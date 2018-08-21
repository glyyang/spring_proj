package com.g128.businessdelegate;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

//import com.amazonaws.mws.MarketplaceWebService;
import com.g128.amazonservice.mws.service.GetReport;
import com.g128.amazonservice.mws.service.ListRequestReport;
import com.g128.amazonservice.mws.service.RequestReport;
import com.g128.dao.StartUpDao;
import com.g128.model.Components;
import com.g128.model.MWSEnv;
import com.g128.model.Money;
import com.g128.model.OrderItem;
import com.g128.model.OrderMessage;
import com.g128.model.Orders;
import com.g128.model.Promotion;
import com.g128.model.XmlMoney;

public class ReportDelegation {
	private String accessKeyId;
	private String secretAccessKey;
	private String merchantId;
	private String sellerDevAuthToken;
	private StartUpDao sDao;
	
	public void setCredential(String kid, String akey, String mid, String auth) {
		accessKeyId = kid;
		secretAccessKey = akey;
		merchantId = mid;
		sellerDevAuthToken = auth;
		sDao = new StartUpDao();
	}
	
	public void fetchingAllReport() throws JAXBException, InterruptedException, ParseException, IOException, SOAPException {
		Date sdt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		sdt = sdf.parse("06/01/2015 00:00:00");
			
		
		long startTime = sdt.getTime();
		//System.out.println(sDao.getMaxTimeORec().get(0));
		
		
		
		//uncomment when fixed
		//if(!sDao.getMaxTimeORec().isEmpty() && sDao.getMaxTimeORec().get(0) != null && sDao.getMaxTimeORec() != null)			startTime = sDao.getMaxTimeORec().get(0).getTime();
		
//		long hold = Long.parseLong("108864000000");
		
//		startTime = startTime + hold;
		sdt = sdf.parse("01/01/2014 00:00:00");
		long endTime = sdt.getTime();
		//long endTime = System.currentTimeMillis()+86400000;
		//long j = -1;
		
		//Cannot do this, hibernate auto-generates primary key attribute value at runtime on persisted/saved objects,
		//meaning that the older values in the list will actually try to persist itself if exist and update the related entities(table)
		//instead of creating new ones(through mapping, somehow);
		//List<Orders> ordList = new ArrayList<>();
		long checker = 0;
		for(long i = startTime; i < endTime; i+= (Long.parseLong("864000000"))) {
			List<Orders> ordList = new ArrayList<>();
			checker++;
			System.out.println("Plus 1 month: " + checker);
			RequestReport.setCredential(accessKeyId, secretAccessKey, merchantId, sellerDevAuthToken);
			ListRequestReport.setCredential(accessKeyId, secretAccessKey, merchantId, sellerDevAuthToken);
			GetReport.setCredential(accessKeyId, secretAccessKey, merchantId, sellerDevAuthToken);
			
			
			String requestId = RequestReport.invokeRequestReport(i, i+(Long.parseLong("863999999")), "_GET_XML_ALL_ORDERS_DATA_BY_ORDER_DATE_");
			String reportId = null;
			while(reportId == null || reportId.equals("")) {
				Thread.sleep(60000);
				reportId = ListRequestReport.invokeGetReportRequestList(requestId);
			}
			String xmlString = GetReport.invokeGetReport(reportId);
			
//			xmlString = xmlString.replace( " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amzn-envelope.xsd\"", "");
//			xmlString = xmlString.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
			
			sDao.logCurrentLogger("Fetching from report", 0, 0, new Timestamp(i), 60000);
//			File f = new File("report.xml");
//			FileWriter fi = new FileWriter(f);
//			fi.write(xmlString);
//			//fi.flush();
//			fi.close();
//			System.out.println("writen");
			//SOAPMessage message = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(xmlString.getBytes()));
			//JAXBContext jaxc = JAXBContext.newInstance("com.g128.model", com.g128.amazonservice.mws.client.MarketplaceWebService.class.getClassLoader());
			JAXBContext jaxc = JAXBContext.newInstance(MWSEnv.class);
			
			Unmarshaller umsl = jaxc.createUnmarshaller();
//			System.out.println("write");
			//System.out.println(xmlString);
			//System.out.println("written");
			StringReader reader = new StringReader(xmlString);
//			MWSEnv env = (MWSEnv) umsl.unmarshal(message.getSOAPBody().extractContentAsDocument());
			
			MWSEnv env = (MWSEnv) umsl.unmarshal(reader);
			List<OrderMessage> holderList = env.getLmsg();
			for(OrderMessage om : holderList) {
				List<Orders> ordHL = om.getOrds();
				for(Orders ord : ordHL) {
					ord.setSellerOrderId(ord.getAmazonOrderId());
					ord.setCity(ord.getFfmt().getAddr().getCity());
					ord.setCountryCode(ord.getFfmt().getAddr().getCountryCode());
					ord.setPostalCode(ord.getFfmt().getAddr().getPostalCode());
					ord.setStateOrRegion(ord.getFfmt().getAddr().getStateOrRegion());
					ord.setFulfillmentChannel(ord.getFfmt().getFfmtCh());
					ord.setShipmentServiceLevelCategory(ord.getFfmt().getShipServ());
					ord.setShipServiceLevel(ord.getFfmt().getShipServ());
					//System.out.println(ord.getOrd_items());
					for(OrderItem oi : ord.getOrd_items()) {
						//System.out.println(oi);
//						XmlMoney m = new XmlMoney();
//						m.setAmount(oi.getItemPrice().getAmount());
//						m.setCurrencyCode(oi.getItemPrice().getCurrencyCode());
//						m.setCmp(cmp);
						XmlMoney m = (XmlMoney)oi.getItem_price();
						//System.out.println(m);
						double amt = 0;
						String crncy = "";
						String type;
						Money mny = new Money();
						if(m!= null) {
							List<Components> cmps = m.getCmp();
							for(Components cmp: cmps) {
								amt += Double.parseDouble(cmp.getAmount().getCost());
								crncy = cmp.getAmount().getCurrency();
								type = cmp.getType();
								
							}
							mny.setAmount(amt+"");
							mny.setCurrencyCode(crncy);
							oi.setItemPrice(mny);
						}
						if(oi.getPromo() != null) {
							Promotion promotion = oi.getPromo();
							if(promotion.getPromo_ids()!=null)
								oi.setPromotionIds(Arrays.asList(promotion.getPromo_ids().split("\\s+")));
							mny = new Money();
							mny.setCurrencyCode(crncy);
							mny.setAmount(promotion.getShipPromoDisc());
							oi.setShippingDiscount(mny);
						}
						oi.setOrd(ord);
						
					}
					GregorianCalendar greg = ord.getPurchaseDate().toGregorianCalendar();
					Timestamp tst = new Timestamp(greg.getTimeInMillis());
					ord.setPurchaseDateConverted(tst);
					
					greg = ord.getLastUpdateDate().toGregorianCalendar();
					tst.setTime(greg.getTimeInMillis());
					ord.setLastUpdated(tst);
					ordList.add(ord);
//					System.out.println(ord.getOrd_items());
					sDao.logCurrentRecord("Saving Order info.", System.currentTimeMillis(), ord.getPurchaseDateConverted());
				}
			}
			
			sDao.startUpOrderUpdate(ordList);
		}
		
		
	}
}

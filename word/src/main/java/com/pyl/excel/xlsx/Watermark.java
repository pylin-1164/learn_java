package com.pyl.excel.xlsx;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.SpreadsheetMLPackage;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.VMLPart;
import org.docx4j.openpackaging.parts.SpreadsheetML.PrinterSettings;
import org.docx4j.openpackaging.parts.SpreadsheetML.WorksheetPart;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPart;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.relationships.Relationship;
import org.docx4j.vml.root.Xml;
import org.xlsx4j.jaxb.Context;
import org.xlsx4j.sml.CTHeaderFooter;
import org.xlsx4j.sml.CTLegacyDrawing;
import org.xlsx4j.sml.CTPageMargins;
import org.xlsx4j.sml.CTPhoneticPr;
import org.xlsx4j.sml.CTSheetBackgroundPicture;
import org.xlsx4j.sml.CTSheetDimension;
import org.xlsx4j.sml.CTSheetFormatPr;
import org.xlsx4j.sml.Cell;
import org.xlsx4j.sml.Row;
import org.xlsx4j.sml.STOrientation;
import org.xlsx4j.sml.STPhoneticType;
import org.xlsx4j.sml.SheetData;
import org.xlsx4j.sml.SheetView;
import org.xlsx4j.sml.SheetViews;
import org.xlsx4j.sml.Worksheet;

import com.pyl.image.FontImage;

/**
 * <ns2:worksheet xmlns:ns2="http://schemas.openxmlformats.org/spreadsheetml/2006/main" xmlns:ns4="http://schemas.microsoft.com/office/excel/2006/main" xmlns:xdr="http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing" xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships" xmlns:ns5="http://schemas.microsoft.com/office/excel/2008/2/main">
    <ns2:dimension ref="A1"/>
    <ns2:sheetViews>
        <ns2:sheetView tabSelected="true" workbookViewId="0"/>
    </ns2:sheetViews>
    <ns2:sheetFormatPr defaultRowHeight="13.5"/>
    <ns2:sheetData>
        <ns2:row r="1" spans="1:1">
            <ns2:c r="A1" t="s">
                <ns2:v>0</ns2:v>
            </ns2:c>
        </ns2:row>
    </ns2:sheetData>
    <ns2:phoneticPr fontId="1" type="noConversion"/>
    <ns2:pageMargins left="0.7" right="0.7" top="0.75" bottom="0.75" header="0.3" footer="0.3"/>
    <ns2:headerFooter>
        <ns2:oddHeader>&amp;G</ns2:oddHeader>
    </ns2:headerFooter>
    <ns2:legacyDrawingHF r:id="rId1"/>
    <ns2:picture r:id="rId2"/>
</ns2:worksheet>

 * @author Administrator
 *
 */
public class Watermark {
	
	String fileName = System.getProperty("user.dir") + "/waterMarksample.xlsx";
//	String fileName = "d:/word/print/test4.xlsx";
	String waterMark = "WATER-MARK";
	String outMarkPic = System.getProperty("user.dir") + "/waterMark.png";
	
	public void addWatermark() throws Exception{
		//创建水印图片
		FontImage.createImage(waterMark, new Font("宋体", Font.PLAIN, 100), new File(outMarkPic));
		
//		SpreadsheetMLPackage pkg = SpreadsheetMLPackage.load(new File(fileName));
//		WorksheetPart worksheet = pkg.getWorkbookPart().getWorksheet(0);
		SpreadsheetMLPackage pkg = SpreadsheetMLPackage.createPackage();
		WorksheetPart worksheet = pkg.createWorksheetPart(new PartName("/xl/worksheets/sheet1.xml"), "Sheet1", 1);
		addContent(worksheet);
		
		drawingHF(pkg, worksheet);
		
		createBgPic(pkg, worksheet);
		
		System.out.println(worksheet.getXML());
		
		/*SaveToZipFile saver = new SaveToZipFile(pkg);
		saver.save(fileName);*/
		pkg.save(new File(fileName));
		System.out.println("\n\n done .. " + fileName);
	}
	
	private  void setBasic(WorksheetPart sheet){
		CTPageMargins pageMargins = org.xlsx4j.jaxb.Context.getsmlObjectFactory().createCTPageMargins();
		CTPhoneticPr phoneticPr = org.xlsx4j.jaxb.Context.getsmlObjectFactory().createCTPhoneticPr();
		SheetViews sheetViews = org.xlsx4j.jaxb.Context.getsmlObjectFactory().createSheetViews();
		SheetView sheetView = org.xlsx4j.jaxb.Context.getsmlObjectFactory().createSheetView();
		CTSheetDimension dimension = org.xlsx4j.jaxb.Context.getsmlObjectFactory().createCTSheetDimension();
		CTSheetFormatPr sheetFormatPr = org.xlsx4j.jaxb.Context.getsmlObjectFactory().createCTSheetFormatPr(); 
		sheetFormatPr.setDefaultRowHeight(13.5);
		dimension.setRef("A1");
		sheetViews.getSheetView().add(sheetView);
		sheetView.setWorkbookViewId(0);
		sheetView.setTabSelected(true);
		phoneticPr.setType(STPhoneticType.NO_CONVERSION);
		phoneticPr.setFontId(0L);
		pageMargins.setLeft(0.7d);
		pageMargins.setRight(0.7d);
		pageMargins.setBottom(0.75d);
		pageMargins.setFooter(0.3d);
		sheet.getJaxbElement().setDimension(dimension);
		sheet.getJaxbElement().setSheetViews(sheetViews);
		sheet.getJaxbElement().setSheetFormatPr(sheetFormatPr);
		sheet.getJaxbElement().setPhoneticPr(phoneticPr);
		sheet.getJaxbElement().setPageMargins(pageMargins);
	} 
	
	private  void addContent(WorksheetPart sheet) {
		setBasic(sheet);
		
		// Minimal content already present
		SheetData sheetData = sheet.getJaxbElement().getSheetData();

		// Now add
		Row row = Context.getsmlObjectFactory().createRow();
		// row.setR((long) 1); // optional

		Cell cell = Context.getsmlObjectFactory().createCell();
		cell.setV("1234");
		cell.setR("A1"); // Apple Numbers needs this, or cell content won't show
		row.getC().add(cell);


		sheetData.getRow().add(row);
	}


	/**
	 * 使用水印图片作为excel背景，达到水印效果<但打印时不会生效>
	 * @param pkg
	 * @param worksheet
	 * @throws Exception
	 * @throws IOException
	 */
	private void createBgPic(SpreadsheetMLPackage pkg, WorksheetPart worksheet) throws Exception, IOException {
		CTSheetBackgroundPicture ctSheetBackgroundPicture = org.xlsx4j.jaxb.Context.getsmlObjectFactory().createCTSheetBackgroundPicture();
		worksheet.getJaxbElement().setPicture(ctSheetBackgroundPicture);
		
		
		BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(pkg, worksheet, FileUtils.readFileToByteArray(new File(outMarkPic)));
		Relationship sourceRelationship = imagePart.getSourceRelationship();
		String imageRelID =sourceRelationship.getId();
		ctSheetBackgroundPicture.setId(imageRelID);
	}
	
	/**
	 * 设置头信息，插入图片实现打印带水印
	 * @param pkg
	 * @param worksheet
	 * @throws IOException
	 * @throws Exception
	 * @return imgId
	 */
	@SuppressWarnings("deprecation")
	private void drawingHF(SpreadsheetMLPackage pkg ,WorksheetPart worksheet) throws IOException, Exception{
		VMLPart vmlPart = new VMLPart();
		worksheet.addTargetPart(vmlPart);

		CTLegacyDrawing legacyDrawing = org.xlsx4j.jaxb.Context.getsmlObjectFactory().createCTLegacyDrawing();

		BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(pkg, vmlPart,
				FileUtils.readFileToByteArray(new File(outMarkPic)));
		
		headSetting(worksheet, imagePart);
		
		String imageRelID = imagePart.getSourceRelationship().getId();
		vmlPart.setJaxbElement(buildDrawingPartContentFromXmlString(imageRelID));
		
		legacyDrawing.setId(imageRelID);
		Worksheet jaxbElement = worksheet.getJaxbElement();
		jaxbElement.setLegacyDrawingHF(legacyDrawing);
		
	}
	
	/**
	 * 设置头信息<插入图片>
	 * @param worksheet
	 * @param binaryPart
	 * @throws Exception
	 */
	public void headSetting(WorksheetPart worksheet,BinaryPart binaryPart) throws Exception{

		CTHeaderFooter headerFooter = org.xlsx4j.jaxb.Context.getsmlObjectFactory().createCTHeaderFooter();
		headerFooter.setOddHeader("&G");//插入图片
		worksheet.getJaxbElement().setHeaderFooter(headerFooter);
		
//		printSetting(worksheet);
		
	}

	/**
	 * 打印设置<br>
	 * PS:如果只配置打印水印，不配置背景水印情况下，需要添加该属性
	 * @param worksheet
	 * @throws InvalidFormatException
	 * @throws FileNotFoundException
	 */
	private void printSetting(WorksheetPart worksheet) throws InvalidFormatException, FileNotFoundException {
		PrinterSettings printerSettings = new PrinterSettings();
		Relationship printerRel = worksheet.addTargetPart(printerSettings);
		
		printerSettings.setBinaryData(new FileInputStream(outMarkPic));
		
		org.xlsx4j.sml.CTPageSetup pageSetup = org.xlsx4j.jaxb.Context.getsmlObjectFactory().createCTPageSetup();
		pageSetup.setId(printerRel.getId());
		pageSetup.setPaperSize(9L);
		pageSetup.setOrientation(STOrientation.PORTRAIT);
		pageSetup.setHorizontalDpi(100L);
		pageSetup.setVerticalDpi(100L);
		worksheet.getJaxbElement().setPageSetup(pageSetup);
	}
	
	/**
	 * This code generated using
	 * http://webapp.docx4java.org/OnlineDemo/PartsList.html "Method 2"
	 * @throws JAXBException 
	 */
	private  Xml buildDrawingPartContentFromXmlString(String imageRelID) throws JAXBException  {

		String openXML = "<xml xmlns:v=\"urn:schemas-microsoft-com:vml\""
						+ " xmlns:o=\"urn:schemas-microsoft-com:office:office\""
						+ " xmlns:x=\"urn:schemas-microsoft-com:office:excel\">"
						+ " <o:shapelayout v:ext=\"edit\">"
						+ " <o:idmap v:ext=\"edit\" data=\"1\"/>"
						+ " </o:shapelayout><v:shapetype id=\"_x0000_t75\" coordsize=\"21600,21600\" o:spt=\"75\""
						+ "  o:preferrelative=\"t\" path=\"m@4@5l@4@11@9@11@9@5xe\" filled=\"f\" stroked=\"f\">"
						+ "<v:stroke joinstyle=\"miter\"/>"
						+ "<v:formulas>"
						+ "<v:f eqn=\"if lineDrawn pixelLineWidth 0\"/>"
						+ "<v:f eqn=\"sum @0 1 0\"/>"
						+ "<v:f eqn=\"sum 0 0 @1\"/>"
						+ "<v:f eqn=\"prod @2 1 2\"/>"
						+ "<v:f eqn=\"prod @3 21600 pixelWidth\"/>"
						+ "<v:f eqn=\"prod @3 21600 pixelHeight\"/>"
						+ "<v:f eqn=\"sum @0 0 1\"/>"
						+ "<v:f eqn=\"prod @6 1 2\"/>"
						+ "<v:f eqn=\"prod @7 21600 pixelWidth\"/>"
						+ "<v:f eqn=\"sum @8 21600 0\"/>"
						+ "<v:f eqn=\"prod @7 21600 pixelHeight\"/>"
						+ "<v:f eqn=\"sum @10 21600 0\"/>"
						+ "</v:formulas>"
						+ "<v:path o:extrusionok=\"f\" gradientshapeok=\"t\" o:connecttype=\"rect\"/>"
						+ "<o:lock v:ext=\"edit\" aspectratio=\"t\"/>"
						+ "</v:shapetype><v:shape id=\"CH\" o:spid=\"_x0000_s1025\" type=\"#_x0000_t75\""
						+ " style='position:absolute;margin-left:0;margin-top:0;width:1014.75pt;height:263.25pt;"
						+ " z-index:1' o:preferrelative=\"f\">"
						+ "<v:imagedata o:relid=\""+imageRelID+"\" o:title=\"b4000\"/>"
						+ "<o:lock v:ext=\"edit\" rotation=\"t\" aspectratio=\"f\"/>"
						+ "</v:shape></xml>";
		System.out.println(openXML);
//		Xml xml = new org.docx4j.vml.root.ObjectFactory().createXml();
		return (Xml) XmlUtils.unwrap(XmlUtils.unmarshalString(openXML));

	}
	
	public static void main(String[] args) {
		try {
			new Watermark().addWatermark();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

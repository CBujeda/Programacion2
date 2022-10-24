package zz_pruebasYrepaso.generadorXML;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		File f = new File("C:\\eclip\\datos2.txt");
		try {
			Scanner sc = new Scanner(f);
			Formatter fo = new Formatter(new File("C:\\eclip\\facturas.xml"));
			fo.format("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
			fo.format("<facturas>\n");
			if(sc.hasNextLine()) {
				sc.nextLine();
			}
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				line = line.replaceAll("-", "");
				String[] datos = line.split(";");
				datos[1] = datos[1].replaceAll("/", "-");
				datos[14] = datos[14].replaceAll("/","-");
				fo.format("	<factura>\n");
				
				fo.format(
						  "		<nfactura>\n"
						+ "			"+datos[0]+"\n"
						+ "		</nfactura>\n");
				fo.format(
						  "		<fechaFactura>\n"
						+ "			"+datos[1]+"\n"
						+ "		</fechaFactura>\n");
				
				fo.format(
						  "		<nombreFacturado>\n"
						+ "			"+datos[2]+"\n"
						+ "		</nombreFacturado>\n");
				fo.format(
						  "		<cif>\n"
						+ "			"+datos[3]+"\n"
						+ "		</cif>\n");
				fo.format(
						  "		<companyFacturado>\n"
						+ "			"+datos[4]+"\n"
						+ "		</companyFacturado>\n");
				fo.format(
						  "		<direccionFacturado>\n"
						+ "			"+datos[5]+"\n"
						+ "		</direccionFacturado>\n");
				fo.format("		<ciudadFacturado>\n"
						+ "			"+datos[6]+"\n"
						+ "		</ciudadFacturado>\n");
				fo.format("		<codPostFacturado>\n"
						+ "			"+datos[7]+"\n"
						+ "		</codPostFacturado>\n");
				fo.format("		<telefonoFacturado>\n"
						+ "			"+datos[8]+"\n"
						+ "		</telefonoFacturado>\n");
				fo.format("		<concepto>\n"
						+ "			"+datos[9]+"\n"
						+ "		</concepto>\n");
				fo.format("		<precio>\n"
						+ "			"+datos[10]+"\n"
						+ "		</precio>\n");
				fo.format("		<unidades>\n"
						+ "			"+datos[11]+"\n"
						+ "		</unidades>\n");
				fo.format("		<iva>\n"
						+ "			"+datos[12]+"\n"
						+ "		</iva>\n");
				fo.format("		<moneda>\n"
						+ "			"+datos[13]+"\n"
						+ "		</moneda>\n");
				fo.format("		<fechaCobro>\n"
						+ "			"+datos[14]+"\n"
						+ "		</fechaCobro>\n");
				fo.format("		<apellidos>\n"
						+ "			"+datos[15]+"\n"
						+ "		</apellidos>\n");
				fo.format("		<state>\n"
						+ "			"+datos[16]+"\n"
						+ "		</state>\n");
				fo.format("	</factura>\n");
				
			}
			fo.format("</facturas>\n");
			sc.close();
			fo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

package xml2;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class bai2{

    private static final String FILE_NAME = "src/company.xml";

    public static void readDataFromXML() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println(" File not found!");
                return;
            }

            // Tạo Document từ file XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            // Lấy danh sách các phần tử employee
            NodeList employeeList = doc.getElementsByTagName("employee");

            System.out.println("\n=== EMPLOYEE LIST ===");
            for (int i = 0; i < employeeList.getLength(); i++) {
                Node node = employeeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element employee = (Element) node;

                    // Đọc ID
                    String id = employee.getAttribute("id");
                    System.out.println("\n Employee ID: " + id);

                    // Đọc Name
                    String name = employee.getElementsByTagName("name").item(0).getTextContent();
                    System.out.println("    Name: " + name);

                    // Đọc Contact
                    Element contact = (Element) employee.getElementsByTagName("contact").item(0);
                    String email = contact.getElementsByTagName("email").item(0).getTextContent();
                    String phone = contact.getElementsByTagName("phone").item(0).getTextContent();
                    System.out.println("    Contact:");
                    System.out.println("        Email: " + email);
                    System.out.println("        Phone: " + phone);

                    // Đọc Department
                    Element department = (Element) employee.getElementsByTagName("department").item(0);
                    String departmentName = department.getElementsByTagName("name").item(0).getTextContent();
                    String location = department.getElementsByTagName("location").item(0).getTextContent();
                    System.out.println("    Department:");
                    System.out.println("        Name: " + departmentName);
                    System.out.println("        Location: " + location);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hàm chính để chạy chương trình
    public static void main(String[] args) {
        System.out.println(" Reading complex XML data...");
        readDataFromXML();
    }
}
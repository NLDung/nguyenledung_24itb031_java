package xml2;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class bai134 {
    private static final String FILE_NAME = "students.xml";

    // Thêm sinh viên vào file XML
    public static void addStudent(int id, String name, int age, String major) {
        try {
            File file = new File(FILE_NAME);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc;

            if (file.exists()) {
                doc = builder.parse(file);
            } else {
                doc = builder.newDocument();
                Element rootElement = doc.createElement("students");
                doc.appendChild(rootElement);
            }

            Element root = doc.getDocumentElement();

            // Tạo phần tử sinh viên
            Element student = doc.createElement("student");
            student.setAttribute("id", String.valueOf(id));

            Element nameElement = doc.createElement("name");
            nameElement.appendChild(doc.createTextNode(name));
            student.appendChild(nameElement);

            Element ageElement = doc.createElement("age");
            ageElement.appendChild(doc.createTextNode(String.valueOf(age)));
            student.appendChild(ageElement);

            Element majorElement = doc.createElement("major");
            majorElement.appendChild(doc.createTextNode(major));
            student.appendChild(majorElement);

            root.appendChild(student);

            // Ghi vào file
            writeToFile(doc);

            System.out.println(" Student added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xóa sinh viên theo ID
    public static void deleteStudent(int id) {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println(" File not found!");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList students = doc.getElementsByTagName("student");
            boolean found = false;

            for (int i = 0; i < students.getLength(); i++) {
                Element student = (Element) students.item(i);
                if (Integer.parseInt(student.getAttribute("id")) == id) {
                    student.getParentNode().removeChild(student);
                    found = true;
                    break;
                }
            }

            if (found) {
                writeToFile(doc);
                System.out.println(" Student deleted successfully!");
            } else {
                System.out.println(" Student ID not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cập nhật sinh viên theo ID
    public static void updateStudent(int id, String name, int age, String major) {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println(" File not found!");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList students = doc.getElementsByTagName("student");
            boolean found = false;

            for (int i = 0; i < students.getLength(); i++) {
                Element student = (Element) students.item(i);
                if (Integer.parseInt(student.getAttribute("id")) == id) {
                    student.getElementsByTagName("name").item(0).setTextContent(name);
                    student.getElementsByTagName("age").item(0).setTextContent(String.valueOf(age));
                    student.getElementsByTagName("major").item(0).setTextContent(major);
                    found = true;
                    break;
                }
            }

            if (found) {
                writeToFile(doc);
                System.out.println(" Student updated successfully!");
            } else {
                System.out.println(" Student ID not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Ghi dữ liệu vào file XML
    private static void writeToFile(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(FILE_NAME));
        transformer.transform(source, result);
    }

    // Hiển thị menu cho người dùng
    public static void displayMenu() {
        System.out.println("\n==== STUDENT MANAGEMENT MENU ====");
        System.out.println("1. Add Student");
        System.out.println("2. Delete Student");
        System.out.println("3. Update Student");
        System.out.println("4. Exit");
        System.out.print(" Choose an option: ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            choice = sc.nextInt();
            sc.nextLine(); // Đọc ký tự xuống dòng

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter student age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter student major: ");
                    String major = sc.nextLine();

                    addStudent(id, name, age, major);
                    break;

                case 2:
                    System.out.print("Enter student ID to delete: ");
                    int deleteId = sc.nextInt();
                    deleteStudent(deleteId);
                    break;

                case 3:
                    System.out.print("Enter student ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new student name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter new student age: ");
                    int newAge = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new student major: ");
                    String newMajor = sc.nextLine();

                    updateStudent(updateId, newName, newAge, newMajor);
                    break;

                case 4:
                    System.out.println(" Exiting program...");
                    break;

                default:
                    System.out.println(" Invalid option. Please try again!");
                    break;
            }
        } while (choice != 4);

        sc.close();
    }
}
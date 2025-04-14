package bai1;


import java.io.*;
public class bai1 {
    public static void main(String[] args) {
        String filename = "D:\\JAVA\\week4\\src\\bai1\\filein.txt";
        readfile rf = new readfile(filename);
        rf.start();
        try{
            rf.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class readfile extends Thread{
    private String filename;
    public readfile(String filename){
        this.filename = filename;
    }
    public void run(){
        try(BufferedReader br = new BufferedReader(new FileReader(filename));){
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
package Files;

import java.io.File;
//java file I/O is based on unix operating system, Hence java file Object can be used to represent both files and
//directories
public class FileDemo {
    public static void main(String[] args) {
        File f = new File("src//Files//abc.txt");
        //we are creating file object to represent abc.txt name
        File t= new File(System.getProperty("java.io.tmpdir"));
        System.out.println(t);
        File file = new File(t.getAbsolutePath()+"/testFile.txt");
        try{t.createNewFile();}
        catch (Exception e){
            System.out.println(e.toString());
        }
        System.out.println(t.exists());
        System.out.println(f.getAbsolutePath());
        System.out.println(f.exists());
        try{
            f.createNewFile(); //Physical file is created
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        System.out.println(f.exists());
        if(f.delete()){ //delete the file with f as reference
            System.out.println(f.getName()+" deleted");
        }
        else{
            System.out.println(f.getName()+" Not deleted");
        }

        //making a directory

        File f1 = new File("Vasanth");
        System.out.println(f1.exists());
        f1.mkdir(); //makes directory
        System.out.println(f1.exists());
        if(f1.delete()){
            System.out.println(f1.getName()+" deleted");
        }
        else{
            System.out.println(f1.getName()+" Not deleted");
        }

    }
}

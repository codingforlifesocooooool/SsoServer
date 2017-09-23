package Token;


import java.io.ByteArrayInputStream;  
import java.io.ByteArrayOutputStream;  
import java.io.ObjectInputStream;  
import java.io.ObjectOutputStream;  
  
public class SerializeUtil {  
  
    /** 
     *  
     * <p>Title: ObjTOSerialize</p> 
     * <p>Description: ���л�һ������</p> 
     * @param obj 
     * @return 
     * @author guangshuai.wang 
     */  
    public static byte[] ObjTOSerialize(Object obj){  
        ObjectOutputStream oos = null;  
        ByteArrayOutputStream byteOut = null;  
        try{  
            byteOut = new ByteArrayOutputStream();  
            oos = new ObjectOutputStream(byteOut);  
            oos.writeObject(obj);  
            byte[] bytes = byteOut.toByteArray();  
            return bytes;  
        }catch (Exception e) {  
            System.out.println("�������л�ʧ��");  
        }  
        return null;  
    }  
    /** 
     *  
     * <p>Title: unSerialize</p> 
     * <p>Description: �����л�</p> 
     * @param bytes 
     * @return 
     * @author guangshuai.wang 
     */  
    public static Object unSerialize(byte[] bytes){  
        ByteArrayInputStream in = null;  
        try{  
            in = new ByteArrayInputStream(bytes);  
            ObjectInputStream objIn = new ObjectInputStream(in);  
            return objIn.readObject();  
        }catch (Exception e) {  
           System.out.println("�����л�ʧ��");  
        }  
        return null;  
    }  
} 
package vision.srepo;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Set;

/**
 * User: ervjo
 * Date: 2010-mar-01
 * Time: 16:38:57
 * To change this template use File | Settings | File Templates.
 */
public class Checksum {
    private long size;
    private MessageDigest messageDigest;
    private byte[] digiest;

    public Checksum(File file) {
        this.size = size;
        try {
            messageDigest = MessageDigest.getInstance("SHA1");
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream input = new BufferedInputStream(fileInputStream);
            int tmp = 0;
            while ((tmp = input.read()) != 1) {
                messageDigest.update((byte) tmp);
            }

            digiest = messageDigest.digest();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public static void main(String[] args) {
        try {
            MessageDigest m = MessageDigest.getInstance("SHA1");

            System.out.println("m = " + m);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        final Provider[] providers = Security.getProviders();
        for (int i = 0; i < providers.length; i++) {
            Provider provider = providers[i];
            System.out.println("provider = " + provider);
            final Set<Provider.Service> serviceSet = provider.getServices();
            for (Provider.Service service : serviceSet) {
                System.out.println("service = " + service);
            }
        }
    }
}

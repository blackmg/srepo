package vision.srepo;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Arrays;
import java.util.Set;

/**
 * User: ervjo
 * Date: 2010-mar-01
 * Time: 16:38:57
 * To change this template use File | Settings | File Templates.
 */
public class Checksum {
    private final long size;
    private byte[] digiest;
    private int hashcode;

    public Checksum(Path path, long size) {
        this.size = size;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            InputStream fileInputStream = path.newInputStream();
            BufferedInputStream input = new BufferedInputStream(fileInputStream);
            int tmp;
            System.out.println("Checksum.Checksum - reading " + path);

            while ((tmp = input.read()) != -1) {
                messageDigest.update((byte) tmp);
            }
            digiest = messageDigest.digest();
            for (byte b : digiest) {
                hashcode = 31 * hashcode + b;
            }
            input.close();
            fileInputStream.close();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public byte[] getDigiest() {
        return digiest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        Checksum checksum = (Checksum) o;

        if (size != checksum.size) return false;
        if (hashcode != checksum.hashcode) return false;
        if (!Arrays.equals(digiest, checksum.digiest)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return hashcode;
    }

    public static String hexToString(int value, int width) {
        return String.format("%0" + width + "X", value);
    }

    public String getId() {
        String width = Integer.toHexString(Integer.MAX_VALUE);

        return hexToString(hashCode(), width.length());
    }

    @Override
    public String toString() {
        final int length = digiest.length;
        return "Checksum{" +
                "size=" + size +
                ", digiest=" + getId() +
                '}';
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

package util.encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import application.EKitaManager;

public class FileEncryptor {

	private EKitaManager app = null;

    public FileEncryptor(EKitaManager app) {
		this.app = app;

        try {
            encryptFile(this.app.getDataFolder() + File.separator + this.app.DATABASE_DIRECTORY + File.separator + this.app.DATABASE_FILE);
            decryptFile(this.app.getDataFolder() + File.separator + this.app.DATABASE_DIRECTORY + File.separator + this.app.DATABASE_FILE);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Arbitrarily selected 8-byte salt sequence:
    private static final byte[] salt = {
        (byte) 0x43, (byte) 0x76, (byte) 0x95, (byte) 0xc7,
        (byte) 0x5b, (byte) 0xd7, (byte) 0x45, (byte) 0x17 
    };

    //Arbitrarily selected 8-byte salt sequence:
    private static final String pass = "WMN2avjH3krRv_k9orPfzKpDlXfyfLicOztAc_TwEHRovboHeLF31r9NY6ZTp00L";

    private static Cipher makeCipher(Boolean decryptMode) throws GeneralSecurityException{

        //Use a KeyFactory to derive the corresponding key from the passphrase:
        PBEKeySpec keySpec = new PBEKeySpec(pass.toCharArray());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(keySpec);

        //Create parameters from the salt and an arbitrary number of iterations:
        PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, 42);

        /*Dump the key to a file for testing: */
        //FileEncryptor.keyToFile(key);

        //Set up the cipher:
        Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");

        //Set the cipher mode to decryption or encryption:
        if(decryptMode){
            cipher.init(Cipher.ENCRYPT_MODE, key, pbeParamSpec);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, key, pbeParamSpec);
        }

        return cipher;
    }


    /**Encrypts one file to a second file using a key derived from a passphrase:**/
    public static void encryptFile(String fileName)
                                throws IOException, GeneralSecurityException{
        byte[] decData;
        byte[] encData;
        File inFile = new File(fileName);
        
        if(!inFile.exists()) {
        	return;
        }
        
        //Generate the cipher using pass:
        Cipher cipher = FileEncryptor.makeCipher(true);

        //Read in the file:
        FileInputStream inStream = new FileInputStream(inFile);

        int blockSize = 8;
        //Figure out how many bytes are padded
        int paddedCount = blockSize - ((int)inFile.length()  % blockSize );

        //Figure out full size including padding
        int padded = (int)inFile.length() + paddedCount;

        decData = new byte[padded];


        inStream.read(decData);

        inStream.close();

        //Write out padding bytes as per PKCS5 algorithm
        for( int i = (int)inFile.length(); i < padded; ++i ) {
            decData[i] = (byte)paddedCount;
        }

        //Encrypt the file data:
        encData = cipher.doFinal(decData);


        //Write the encrypted data to a new file:
        FileOutputStream outStream = new FileOutputStream(new File(fileName + ".encrypted"));
        outStream.write(encData);
        outStream.close();
        
        inFile.delete();
        
    }


    /**Decrypts one file to a second file using a key derived from a passphrase:**/
    public static void decryptFile(String fileName)
                            throws GeneralSecurityException, IOException{
        byte[] encData;
        byte[] decData;
        File inFile = new File(fileName+ ".encrypted");
        
        if(!inFile.exists()) {
        	return;
        }

        //Generate the cipher using pass:
        Cipher cipher = FileEncryptor.makeCipher(false);

        //Read in the file:
        FileInputStream inStream = new FileInputStream(inFile );
        encData = new byte[(int)inFile.length()];
        inStream.read(encData);
        inStream.close();
        //Decrypt the file data:
        decData = cipher.doFinal(encData);

        //Figure out how much padding to remove

        int padCount = (int)decData[decData.length - 1];

        //Naive check, will fail if plaintext file actually contained
        //this at the end
        //For robust check, check that padCount bytes at the end have same value
        if( padCount >= 1 && padCount <= 8 ) {
            decData = Arrays.copyOfRange( decData , 0, decData.length - padCount);
        }

        //Write the decrypted data to a new file:



        FileOutputStream target = new FileOutputStream(new File(fileName));
        target.write(decData);
        target.close();
        
        inFile.delete();
    }

    /**Record the key to a text file for testing:**/
    @SuppressWarnings("unused")
	private static void keyToFile(SecretKey key){
        try {
            File keyFile = new File("C:\\keyfile.txt");
            FileWriter keyStream = new FileWriter(keyFile);
            String encodedKey = "\n" + "Encoded version of key:  " + key.getEncoded().toString();
            keyStream.write(key.toString());
            keyStream.write(encodedKey);
            keyStream.close();
        } catch (IOException e) {
            System.err.println("Failure writing key to file");
            e.printStackTrace();
        }

    }
}
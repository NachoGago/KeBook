package dam.nachogago.ioc.utils;

import org.aspectj.weaver.Dump;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class Encryption {
    public static final String STRING_KEY = "CqeuiAmTfd6bNaebib6OpQ==";
    public static final String STRING_IV = "XbsFIJK7AiNPjuUX";
    private SecretKey key;
    private byte[] IV;
    private int KEY_SIZE = 128;
    private int T_LEN = 128;


    /**
     * Inicializa la clave y el iv.
     * @param secretKey Clave para la encriptacion.
     * @param IV IV que hace referencia a la clave.
     */
    public void initFromStrings(String secretKey, String IV){
        key = new SecretKeySpec(decode(secretKey), "AES");
        this.IV = decode(IV);
    }

    /**
     * Encripta la cadena de datos.
     * @param message Cadena de datos a encriptar.
     * @return Devuelve la cadena encriptada.
     * @throws Exception
     */
    public String encrypt(String message) throws Exception{
        byte[] messageInBytes = message.getBytes();
        Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(T_LEN, IV);
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);
        return encode(encryptedBytes);
    }

    /**
     * Desencripta la cadena de datos.
     * @param encryptedMessage Cadena de datos a desencriptar.
     * @return Devuelve la cadena desencriptada.
     * @throws Exception
     */
    public String decrypt(String encryptedMessage) throws Exception{
        byte[] messageInBytes = decode(encryptedMessage);
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(T_LEN, IV);
        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] decryptedBytes = decryptionCipher.doFinal(messageInBytes);
        return new String(decryptedBytes);
    }

    /**
     * Transforma la cadena de bytes a tipo String.
     * @param data Datos en formato byte a transformar.
     * @return Devuelve los datos en formato String.
     */
    private String encode(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }

    /**
     * Transforma la cadena de caracteres a tipo byte.
     * @param data Datos en formato String a transformar.
     * @return Devuelve los datos en formato Byte.
     */
    private byte[] decode(String data){
        return Base64.getDecoder().decode(data);
    }

}

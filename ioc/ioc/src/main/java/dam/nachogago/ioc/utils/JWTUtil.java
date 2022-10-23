package dam.nachogago.ioc.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;

/**
 * Author: Nacho Gago Munoz
 */
@Component
public class JWTUtil {
    @Value("${security.jwt.secret}")
    private String key;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;

    private final Logger log = LoggerFactory
            .getLogger(JWTUtil.class);

    private HashMap tokensMap = new HashMap<Long, String>();

    /**
     * Crea un nuevo token.
     * @param id Identificador del usuario que recibira el token.
     * @param subject Correo del usuario.
     * @return Devuelve el token en forma de String.
     */
    public String create(String id, String subject) {

        // Algoritmo para firmar el token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //  Firma el token con nuestra clave secreta
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //  Introduce los datos
        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Construye el JWT y lo serializa.
        return builder.compact();
    }

    /**
     * Metodo para validar y leer el JWT
     *
     * @param jwt Token
     * @return Devuelve el valor del token.
     */
    public String getValue(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as
        // expected)
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key))
                .parseClaimsJws(jwt).getBody();

        return claims.getSubject();
    }

    /**
     * Metodo para validar y leer el JWT
     *
     * @param jwt Token
     * @return Devuelve el id del token.
     */
    public String getKey(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key))
                .parseClaimsJws(jwt).getBody();

        return claims.getId();
    }

    /**
     * Elimina un token de la lista de tokens activos.
     * @param id Identificador del usuario que tiene asignado el token.
     * @return Devuelve true o false dependiendo de si lo ha podido remover correctamente.
     */
    public boolean deleteToken(long id){
        if(findTokenByKey(id)){
            tokensMap.remove(id);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Anade un token a la lista de tokens activos.
     * @param id Identificador del usuario al que le asignaremos el token.
     * @param token Token que vamos a anadir a la lista de tokens activos.
     */
    public void addToken(long id, String token){
        tokensMap.put(id, token);
    }

    /**
     * Comprueba que el token este activo.
     * @param token Token a comprobar.
     * @return Devuelve un booleano indicando si el token esta activo o no.
     */
    public boolean findTokenByValue(String token){
        return tokensMap.containsValue(token);
    }

    /**
     * Comprueba si hay un token asociado al id introducido por parametro.
     * @param id Id del usuario
     * @return Devuelve true o false dependiendo de si ha encontrado la clave en el mapa.
     */
    public boolean findTokenByKey(long id){
        return tokensMap.containsKey(id);
    }

}
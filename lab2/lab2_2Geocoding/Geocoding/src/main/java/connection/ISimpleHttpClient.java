/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.IOException;

import org.apache.http.client.utils.URIBuilder;

/**
 *
 * @author ico
 */
public interface ISimpleHttpClient {
    
    public String doHttpGet(String string) throws IOException;
}

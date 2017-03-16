package siscapereader.webService;

/**
 *
 * @author Roger
 */
public class ServicoWeb {

    public static Boolean gravarPlaca(java.lang.String placa, java.lang.String tpVeic) {
        siscapereader.NewWebServiceService service = new siscapereader.NewWebServiceService();
        siscapereader.NewWebService port = service.getNewWebServicePort();
        return port.gravarPlaca(placa, tpVeic);
    }
}

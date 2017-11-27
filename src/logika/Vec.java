package logika;

 

/*******************************************************************************
 * Třída Vec ...
 *
 *@author     Alena Buchalcevova
 *@version    z kurzu 4IT101 pro školní rok 2014/2015
 */
public class Vec
{
//== Datové atributy (statické i instancí)======================================
    private String jmeno;
    private boolean prenositelna;
    private String imageUrl;

//##############################################################################
//== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *
     */
    public Vec (String jmeno, boolean prenositelna) {
        this.jmeno = jmeno;
        this.prenositelna = prenositelna;
    }

    public Vec (String jmeno, boolean prenositelna, String imageUrl) {
        this.imageUrl = imageUrl;
        this.jmeno = jmeno;
        this.prenositelna = prenositelna;
    }


//== Nesoukromé metody (instancí i třídy) ===============================================
//== Soukromé metody (instancí i třídy) ===========================================
    public String getJmeno () {
        return jmeno;
    }
    public boolean jePrenositelna() {
        return prenositelna;
    }

    /**
     * Getter pro image url
     * 
     * @return imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }
        
        

}


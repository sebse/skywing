package model;

import java.io.Serializable;
/**
 * Created by Paul on 11/27/2016.
 */
public class Flughafen implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
    private String code;
    private String land;
    private String stadt;

    public Flughafen(String _name, String _code, String _land, String _stadt) {
        this.name = _name;
        this.code = _code;
        this.land = _land;
        this.stadt = _stadt;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getLand() { return land; }
    public void setLand(String land) { this.land = land; }

    public String getStadt() { return stadt; }
    public void setStadt(String stadt) { this.stadt = stadt; }
}

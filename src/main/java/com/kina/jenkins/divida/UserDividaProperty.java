package com.kina.jenkins.divida;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;

import hudson.model.UserProperty;

@ExportedBean(defaultVisibility = 999)
public class UserDividaProperty extends UserProperty{
    private int divida;
    private int pago;

    public UserDividaProperty() {
	}
    
    
    @DataBoundConstructor
    public UserDividaProperty(int divida, int pago) {
		this.divida = divida;
		this.pago = pago;
	}


	@Exported
	public int getPago() {
		return pago;
	}


	public void setPago(int pago) {
		this.pago = pago;
	}


	@Exported
	public int getDivida() {
		return divida;
	}

	public void setDivida(int divida) {
		this.divida = divida;
	}

    @Override
    public String toString() {
        return String.format("UserScoreProperty [divida%s, user=%s, pago=%s]", divida, user, pago);
    }





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + divida;
		result = prime * result + pago;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDividaProperty other = (UserDividaProperty) obj;
		if (divida != other.divida)
			return false;
		if (pago != other.pago)
			return false;
		return true;
	}
	

	public int getQuantidadeTotalDivida(){
		return divida - pago;
	}

	public void somaMaisUmaDivida() {
		divida++;
	}
    
    
}

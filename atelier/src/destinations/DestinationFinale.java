package destinations;

import information.Information;
import information.InformationNonConforme;

public class DestinationFinale extends Destination<Boolean>{
	
	
	
	 
	   /** 
	   * un constructeur factorisant les initialisations communes aux r�alisations de la classe abstraite DestinationFinale
	   */
	       public DestinationFinale() {
	        super();
	      }
	       
	       /**
	        * re�oit une information 
	        * @param infoRecue information  l'information  � recevoir
		* @throws InformationNonConforme si information non conforme
	        */
	           public void recevoir(Information <Boolean> infoRecue) throws InformationNonConforme
	           {
	        	   super.informationRecue = infoRecue;
	           }

}

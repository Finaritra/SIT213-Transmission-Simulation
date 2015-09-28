package transmetteurs;

import java.util.LinkedList;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

public class TransmetteurParfait extends Transmetteur<Boolean,Boolean> {

	   
	
	/** 
	   * un constructeur factorisant les initialisations communes aux r�alisations de la classe abstraite Transmetteur 
	   */
	       public TransmetteurParfait() {
	        super();
	      }

	     

	       /**
	         * �met l'information construite par le transmetteur  
	          */
	           public void emettre() throws InformationNonConforme
	           {
	        	   Information<Boolean> newInfo =modify( informationRecue);
	        	  
	        	// �mission vers les composants connect�s  
	               for (DestinationInterface <Boolean> destinationConnectee : destinationsConnectees) {
	                  destinationConnectee.recevoir(newInfo);
	               }
	               super.informationEmise = newInfo;
	                      
	            
	           }



		@Override
		public void recevoir(Information<Boolean> information) throws InformationNonConforme {
			// TODO Auto-generated method stub
			 super.informationRecue = information;   
			 
			 emettre();
		}
	    
		
		/**cr�e une nouvelle instance de Information
		 *@param information Information à modifier (transmetteur parfait on modifie pas)
		 *@return l'information modifié
		*/
		public Information<Boolean> modify(Information<Boolean> information){
			Information<Boolean> info = new Information<Boolean>();
			for(int i=0;i<information.nbElements();i++)
			info.add(information.iemeElement(i));
			/*info.add(true);
      	    info.add(false);*/
			return info;
		}
	     
}

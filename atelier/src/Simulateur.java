
   import sources.*;

   import destinations.*;
   import transmetteurs.*;

   import information.*;

   import visualisations.*;

   import java.util.regex.*;


import java.util.*;
   import java.lang.Math;

	
   import java.io.FileInputStream;
   import java.io.FileNotFoundException;
   import java.io.BufferedWriter;
   import java.io.FileWriter;
   import java.io.IOException;
   import java.io.PrintWriter;
  import java.text.DecimalFormat;

/** La classe Simulateur permet de construire et simuler une chaï¿½ne de transmission composï¿½e d'une Source, d'un nombre variable de Transmetteur(s) et d'une Destination.  
 * @author cousin
 * @author prou
 *
 */
   public class Simulateur {
      	
   /** indique si le Simulateur utilise des sondes d'affichage */
      private          boolean affichage = false;
   /** indique si le Simulateur utilise un message gï¿½nï¿½rï¿½ de maniï¿½re alï¿½atoire */
      private          boolean messageAleatoire = true;
   /** indique si le Simulateur utilise un germe pour initialiser les gï¿½nï¿½rateurs alï¿½atoires */
      private          boolean aleatoireAvecGerme = false;
   /** la valeur de la semence utilisï¿½e pour les gï¿½nï¿½rateurs alï¿½atoires */
      private          Integer seed = null;
   /** la longueur du message alï¿½atoire ï¿½ transmettre si un message n'est pas impose */
      private          int nbBitsMess = 100; 
   /** la chaï¿½ne de caractï¿½res correspondant ï¿½ m dans l'argument -mess m */
      private          String messageString = "100";
   	
   
   
   	
   /** le  composant Source de la chaine de transmission */
      private			  Source <Boolean>  source = null;
   /** le  composant Transmetteur parfait logique de la chaine de transmission */
      private			  Transmetteur <Boolean, Boolean>  transmetteurLogique = null;
   /** le  composant Destination de la chaine de transmission */
      private			  Destination <Boolean>  destination = null;
   	
   
   /** Le constructeur de Simulateur construit une chaï¿½ne de transmission composï¿½e d'une Source <Boolean>, d'une Destination <Boolean> et de Transmetteur(s) [voir la mï¿½thode analyseArguments]...  
   * <br> Les diffï¿½rents composants de la chaï¿½ne de transmission (Source, Transmetteur(s), Destination, Sonde(s) de visualisation) sont crï¿½ï¿½s et connectï¿½s.
   * @param args le tableau des diffï¿½rents arguments.
   *
   * @throws ArgumentsException si un des arguments est incorrect
   *
   */   
      public  Simulateur(String [] args) throws ArgumentsException {
      
      	// analyser et rï¿½cupï¿½rer les arguments
      	
         analyseArguments(args);
      
       Information<Boolean> infoFixe = new Information<Boolean>();
       if( messageAleatoire == false)
       {
    	   String bit;
    	   for(int i = 0; i<nbBitsMess;i++)
    	   {
    		   bit = messageString.substring(i,i+1);
    		   System.out.println(bit); 
    		   if(bit.equals("0"))
    		   {
    			   infoFixe.add(true);
    		   }
    		   else if (bit.equals("1"))
    			   infoFixe.add(false);
    	   }
       }
       else
       {
    	   // create random object
    	   Random randomno = new Random();
    	   for (int i=0;i<nbBitsMess;i++)  
    	        	   infoFixe.add(randomno.nextBoolean());
       }
  	 
  	   
  	   
  	   SondeLogique sonde1 = new SondeLogique("Source",100);
  	   SondeLogique sonde2 = new SondeLogique("TransmissionLogique",100);
  	   
  	   
  	   /** le  composant Source de la chaine de transmission */
  	      source = new SourceFixe(infoFixe);
  	   /** le  composant Transmetteur parfait logique de la chaine de transmission */
  	      transmetteurLogique = new TransmetteurParfait();
  	      /** le  composant Destination de la chaine de transmission */
  	      destination = new DestinationFinale();
  
	  
  	      /**Connexion**/
  	      source.connecter(transmetteurLogique);
  	      source.connecter(sonde1);
  	      transmetteurLogique.connecter(destination);
  	      transmetteurLogique.connecter(sonde2);
      		
      }
   
   
   
   /** La mï¿½thode analyseArguments extrait d'un tableau de chaï¿½nes de caractï¿½res les diffï¿½rentes options de la simulation. 
   * Elle met ï¿½ jour les attributs du Simulateur.
   *
   * @param args le tableau des diffï¿½rents arguments.
   * <br>
   * <br>Les arguments autorisï¿½s sont : 
   * <br> 
   * <dl>
   * <dt> -mess m  </dt><dd> m (String) constituï¿½ de 7 ou plus digits ï¿½ 0 | 1, le message ï¿½ transmettre</dd>
   * <dt> -mess m  </dt><dd> m (int) constituï¿½ de 1 ï¿½ 6 digits, le nombre de bits du message "alï¿½atoire" ï¿½ transmettre</dd> 
   * <dt> -s </dt><dd> utilisation des sondes d'affichage</dd>
   * <dt> -seed v </dt><dd> v (int) d'initialisation pour les gï¿½nï¿½rateurs alï¿½atoires</dd> 
   * <br>
   * <dt> -form f </dt><dd>  codage (String) RZ, NRZR, NRZT, la forme d'onde du signal ï¿½ transmettre (RZ par dï¿½faut)</dd>
   * <dt> -nbEch ne </dt><dd> ne (int) le nombre d'ï¿½chantillons par bit (ne >= 6 pour du RZ, ne >= 9 pour du NRZT, ne >= 18 pour du RZ,  30 par dï¿½faut))</dd>
   * <dt> -ampl min max </dt><dd>  min (float) et max (float), les amplitudes min et max du signal analogique ï¿½ transmettre ( min < max, 0.0 et 1.0 par dï¿½faut))</dd> 
   * <br>
   * <dt> -snr s </dt><dd> s (float) le rapport signal/bruit en dB</dd>
   * <br>
   * <dt> -ti i dt ar </dt><dd> i (int) numero du trajet indirect (de 1 ï¿½ 5), dt (int) valeur du decalage temporel du iï¿½me trajet indirect 
   * en nombre d'ï¿½chantillons par bit, ar (float) amplitude relative au signal initial du signal ayant effectuï¿½ le iï¿½me trajet indirect</dd>
   * <br>
   * <dt> -transducteur </dt><dd> utilisation de transducteur</dd>
   * <br>
   * <dt> -aveugle </dt><dd> les rï¿½cepteurs ne connaissent ni l'amplitude min et max du signal, ni les diffï¿½rents trajets indirects (s'il y en a).</dd>
   * <br>
   * </dl>
   * <br> <b>Contraintes</b> :
   * Il y a des interdï¿½pendances sur les paramï¿½tres effectifs. 
   *
   * @throws ArgumentsException si un des arguments est incorrect.
   *
   */   
      public  void analyseArguments(String[] args)  throws  ArgumentsException {
      		
         for (int i=0;i<args.length;i++){ 
         
              
            if (args[i].matches("-s")){
               affichage = true;
            }
            else if (args[i].matches("-seed")) {
               aleatoireAvecGerme = true;
               i++; 
            	// traiter la valeur associee
               try { 
                  seed =new Integer(args[i]);
               }
                  catch (Exception e) {
                     throw new ArgumentsException("Valeur du parametre -seed  invalide :" + args[i]);
                  }           		
            }
            
            else if (args[i].matches("-mess")){
               i++; 
            	// traiter la valeur associee
               messageString = args[i];
               if (args[i].matches("[0,1]{7,}")) {
                  messageAleatoire = false;
                  nbBitsMess = args[i].length();
               } 
               else if (args[i].matches("[0-9]{1,6}")) {
                  messageAleatoire = true;
                  nbBitsMess = new Integer(args[i]);
                  if (nbBitsMess < 1) 
                     throw new ArgumentsException ("Valeur du parametre -mess invalide : " + nbBitsMess);
               }
               else 
                  throw new ArgumentsException("Valeur du parametre -mess invalide : " + args[i]);
            }
                                   
            else throw new ArgumentsException("Option invalide :"+ args[i]);
         }
      
      }
     
    
   	
   /** La mï¿½thode execute effectue un envoi de message par la source de la chaï¿½ne de transmission du Simulateur. 
   * @return les options explicites de simulation.
   *
   * @throws Exception si un problï¿½me survient lors de l'exï¿½cution
   *
   */ 
      public void execute() throws Exception {      
    	  	
    	
    	
    	      /** émission des données*/
    	      source.emettre();
    	      
    	   
  	      
      }
   
   	   	
   	
   /** La mï¿½thode qui calcule le taux d'erreur binaire en comparant les bits du message ï¿½mis avec ceux du message reï¿½u.
   *
   * @return  La valeur du Taux dErreur Binaire.
   */   	   
      public float  calculTauxErreurBinaire() {
      
    			Information<Boolean> infoSource = source.getInformationEmise();
    			Information<Boolean> infoDestination = destination.getInformationRecue();
    		
    			 
    			float nbErreur = 0;//Initialisation du nombre d'erreur
    			float min = infoDestination.nbElements();
    			System.out.println(infoDestination.nbElements());	
    			
    			if ((infoSource.nbElements() < infoDestination.nbElements())
    					|| (infoSource.nbElements() > infoDestination.nbElements())) {
    				//On regarde le cas ou il y a des bits rajouté ou des bits perdu lors de la transmission

    				nbErreur += Math.abs(infoSource.nbElements() - infoDestination.nbElements());
    				//On ajoute un nombre d'erreur égal à la différence de taille entre l'information reçu et l'information
    				//envoyée par la source
    				min = Math.min(infoSource.nbElements(), infoDestination.nbElements());
    				//min prend la valeur de la plus petite des informations
    			}
    			
    			for (int i = 0; i < min; i++) {
    				if (!infoSource.iemeElement(i).equals(infoDestination.iemeElement(i))) {
    					nbErreur++;
    					//On incremente le nombre d'erreur à chaque fois que les bits corespondant ne sont pas égaux
    				}
    			}

    			 
    			float txErreur = nbErreur / min;
    		    return txErreur;
      	 
      }
   
   
   
   
   /** La fonction main instancie un Simulateur ï¿½ l'aide des arguments paramï¿½tres et affiche le rï¿½sultat de l'exï¿½cution d'une transmission.
   *  @param args les diffï¿½rents arguments qui serviront ï¿½ l'instanciation du Simulateur.
   */
      public static void main(String [] args) { 
      
         Simulateur simulateur = null;
      	
         try {
            simulateur = new Simulateur(args);
         }
            catch (Exception e) {
               System.out.println(e); 
               System.exit(-1);
            } 
      		
         try {
            simulateur.execute();
           float tauxErreurBinaire = simulateur.calculTauxErreurBinaire();
            String s = "java  Simulateur  ";
            for (int i = 0; i < args.length; i++) {
         		s += args[i] + "  ";
         	}
            System.out.println(s + "  =>   TEB : " + tauxErreurBinaire);
         }
            catch (Exception e) {
               System.out.println(e);
               e.printStackTrace();
               System.exit(-2);
            }              	
      }
   	
   }


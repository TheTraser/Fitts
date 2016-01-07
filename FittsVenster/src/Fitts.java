import java.awt.Rectangle;

public class Fitts { 
	// Dit zorgt ervoor dat deze variabelen in de hele class gebruikt kunnen worden
	public static double ID;
	public static int i = 0;
	public static double somB = 0.0;
	
	public static Rectangle getLocatie(int vensterBreedte, int vensterHoogte) {
		
		// Declareer variabelen
		int breedte, hoogte, x, y, z, minbreedte, minhoogte;
		
		// Hier worden de minimale hoogte en breedte van de doelknop bepaald
		minhoogte = 25;
		minbreedte = 100;
	
		// Hier worden de breedte en hoogte van de doelknop bepaald
		breedte = minbreedte + (int)( Math.random()* 200);
		hoogte = minhoogte + (int)( Math.random()* 50);
		
		// Hier wordt de locatie van de doelknop gegenereert 
		y = (int)( Math.random() * ( vensterHoogte - hoogte - 1) + 1); 
		x = (int)( Math.random() * ( vensterBreedte - breedte -1 ) + 1); 
		
		/* Hierdoor beweegt de target button niet zowel langs de y als de x-as. 
		 * z genereert een random variable die of 0 of 1 is */
				z = (int)( Math.random() * 2 );  
				// Hier beweegt de doelknop langs de y-as
				if ( z == 0 ) { 
					x = vensterBreedte / 2 - breedte / 2; 
				}
				// Hier beweegt de doelknop langs de x-as
				else { 
					y = vensterHoogte / 2 - hoogte / 2; 
				}
				
				// Hier wordt de afstand van het doel tot het midden van het venster berekend 
				double Afstandx = Math.abs( (double) x - ( vensterBreedte / 2) );
				double Afstandy = Math.abs( (double) y - ( vensterBreedte / 2) ); 

				// Hier wordt the index of difficulty berekend volgens de formule ID = log2 ( D/W + 1 )
				if ( z == 0 ) { 
					ID = Math.log10(( Afstandy / breedte ) + 1) / Math.log10(2);
				}
				else { 
					ID = Math.log10(( Afstandx / breedte ) + 1 ) / Math.log10(2);
				} 
			
		// Geef de grootte en locatie terug als een gecombineerde waarde
		return new Rectangle(x, y, breedte, hoogte);
		
	} 
	
	public static long startTime;
		//Dit zorgt ervoor dat startTime geldt voor de hele functie
	
	public static void start() {
		// Deze code wordt uitgevoerd als op de startknop wordt geklikt
		
		// Dit start de timer als op de startknop wordt gedrukt
		startTime = System.nanoTime();
		
	}

	public static void stop() {
		// Deze code wordt uitgevoerd als op de doelknop wordt geklikt
		
		// Dit stopt de timer als op de doelknop wordt gedrukt
		long endTime = System.nanoTime();
		// Dit meet de tijd tussen start en stop
		long totalTime = endTime - startTime;
		// Dit converteert nanoseconden naar milliseconden 
		long totalTimemilli = totalTime / 1000000; 
		System.out.println("De tijd tussen start en stop was " + 
		totalTimemilli + " milliseconden");
		
		// Dit print de waarde van ID afgerond op 6 decimalen
		System.out.println("ID = " + String.format("%.6f", ID));
		
		/* Hier wordt b berekend met de formule T / ID 
		 * ( a kan weggelaten worden uit de vergelijking sinds a = 0 )
		 */
		double b = ( totalTimemilli ) / ID;
		
		// Hier worden de waarden van b opgeteld en het gemiddelde berekend
		double gemiddeldeb;
		somB = somB + b; 
		i++;
		gemiddeldeb = somB / i; 
		
		// Dit print het gemiddelde van b afgerond op 2 decimalen
		System.out.println("Het gemiddelde van b is " + String.format("%.2f", gemiddeldeb ));
		System.out.println();
		
	} 
}
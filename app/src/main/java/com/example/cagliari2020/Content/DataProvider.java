package com.example.cagliari2020.Content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataProvider 
{
	public static final String CATEGORY="CATEGORY";
	public static final String COOKING_CATEGORY="CUCINA";
	public static final String TECH_CATEGORY="TECH";
	public static final String GREEN_CATEGORY="GREEN";
	
	private static final String[] CATEGORY_ORDER=new String[]{COOKING_CATEGORY,GREEN_CATEGORY,TECH_CATEGORY};
	
	public static class Content
	{
		private String title;
		private String description;
		public String getTitle() {
			return title;
		}
		public Content setTitle(String title) {
			this.title = title;
			return this;
		}
		public String getDescription() {
			return description;
		}
		public Content setDescription(String description) {
			this.description = description;
			return this;
		}
		
		@Override
		public String toString() {
			return title;
		}
		
		
	}
	
	private static HashMap<String,ArrayList<Content>> contents;
	
	static
	{
		contents=new HashMap<String,ArrayList<Content>>();
		ArrayList<Content> articles=new ArrayList<Content>();
		articles.add(new Content().setTitle("Android 5.0 Lollipop arriva su Samsung Galaxy S5").setDescription("L’aggiornamento OTA (over-the-air) ufficiale al sistema operativo Android 5.0 Lollipop (build LRX21T) è in fase di rollout sullo smartphone Samsung Galaxy S5. Le prime segnalazioni sono giunte nelle ore scorse dalla Polonia, ma la distribuzione dovrebbe prendere il via a breve anche nel resto dei paesi europei. È dunque tempo di preparare il dispositivo per l’update, che di fatto rivoluzionerà l’esperienza di utilizzo con l’introduzione di parecchie nuove feature inedite."));
		articles.add(new Content().setTitle("HP Slate Voicetab Ultra Tablet 3903nl").setDescription("HP Slate Voicetab Ultra Tablet 3903nl è un tablet equipaggiato al lancio con il sistema operativo Android 4.4.2 KitKat. La scheda tecnica vede la presenza di un display da 7 pollici Full HD (risoluzione 1920×1080 pixel), processore quad core HiSilicon Balong V9R1 da 1,6 GHz, 2 GB di RAM, memoria interna da 16 GB, slot per la lettura di schede microSD fino a 32 GB, fotocamera da 13 megapixel con autofocus posizionata sul retro della scocca, secondo obiettivo frontale da 5 megapixel, modulo WiFi, servizio HP DataPass per l’accesso a Internet in mobilità, Bluetooth 4.0, GPS, porta micro-USB 2.0, jack da 3,5 mm per gli auricolari, accelerometro, giroscopio, sensore di prossimità e batteria da 19 Whr."));
		articles.add(new Content().setTitle("Windows 10 Mobile supporterà ARM e Intel").setDescription("Windows 10 in versione mobile potrebbe funzionare non solo su dispositivi dotati di processori con architettura ARM ma anche su dispositivi dotati di processori Intel con architettura X86 anche se meno potenti di quelli che si è soliti trovare sui classici computer e notebook. Questa indiscrezione arriva da Mary Jo Foley di ZDnet che citando le sue fonti interne a Microsoft, sottolinea anche come allo stato attuale, la casa di Redmond avrebbe iniziato i primi test sulla versione mobile di Windows 10 ma solo internamente, in quello che chiama “Operating Systems Group“."));
		articles.add(new Content().setTitle("Journey to Mars, la NASA lancia Orion").setDescription("Alle 13:05 ora italiana verrà lanciata Orion, il veicolo spaziale che porterà gli astronauti su Marte entro il 2030. Il viaggio verso il Pianeta Rosso inizierà oggi dal Kennedy Space Center in Florida, dove è pronto il razzo Delta IV, sulla cui sommità è posizionata la navicella. Dopo i numerosi test effettuati sulla Terra, la NASA verificherà ora il funzionamento dei sistemi di lancio, di rientro nell’atmosfera e di sicurezza, senza la presenza dell’equipaggio."));
		contents.put(TECH_CATEGORY, articles);
		
		articles=new ArrayList<Content>();
		articles.add(new Content().setTitle("Spreco alimentare: 58% degli europei butta cibo buono, italiani attenti").setDescription("Se c’è uno scandalo nella società moderna che dà più fastidio di altri è lo spreco alimentare. Per domani, 5 febbraio 2014, è stata indetta la Giornata nazionale di prevenzione dello spreco alimentare, e per l’occasione Panasonic ha effettuato uno studio per capire qual è la situazione in Europa da questo punto di vista. E la situazione non è per nulla rosea. Nonostante la crisi economica, disoccupazione e tanti altri problemi sociali, più della metà delle persone getta via del cibo buono ogni settimana."));
		articles.add(new Content().setTitle("Utilizzare il computer a letto fa male alla schiena").setDescription("Utilizzare un computer portatile a letto fa male alla schiena. A sostenerlo la British Chiropractic Association (BCA), secondo cui la posizione assunta mentre si guarda lo schermo del proprio dispositivo favorisce l’insorgere di problematiche legate alla postura, come mal di schiena appunto, ma anche dolori al collo."));
		articles.add(new Content().setTitle("Dieta Mediterranea allunga la vita, studio conferma e spiega perché").setDescription("Dieta Mediterranea segreto per vivere più a lungo. Già patrimonio immateriale dell’UNESCO, tale alimentazione rappresenterebbe la chiave per migliorare la propria aspettativa di vita. A sostenerlo uno studio condotto dai ricercatori del Brigham and Women’s Hospital, affiliato alla Harvard Medical School, e della Harvard School of Public Health."));
		articles.add(new Content().setTitle("Rifiuti pericolosi in aumento, in calo quelli speciali secondo ISPRA").setDescription("L’ultimo rapporto dell’ISPRA restituisce dati poco confortanti sulla produzione totale di rifiuti pericolosi in Italia. Dal 2011 al 2012 si è registrato un aumento dell’8,1% pari a 700 mila tonnellate in più rispetto all’anno precedente. Complessivamente i rifiuti pericolosi prodotti in Italia ammontano a 9,4 milioni di tonnellate. Una mole difficile da smaltire, anche alla luce della carenza di strutture adeguate per il trattamento. In tutta la penisola si contano appena 10 impianti attrezzati per lo scopo."));
		contents.put(GREEN_CATEGORY, articles);
		
		articles=new ArrayList<Content>();
		articles.add(new Content().setTitle("Tutti i tipi di sale e le loro varietà in cucina").setDescription("Si dice che per avere una buona insalata l’aceto dovrebbe essere messo da un avaro, l’olio da un pazzo e il sale da un uomo equilibrato. Già, perché il sale è un ingrediente discreto: è presente in quasi tutti i piatti che consumiamo o prepariamo, ma nelle ricette spesso non ha neanche una quantità (solo un q.b.). Se ne avverte la presenza solo quando è troppo, o troppo poco: e così spesso del sale si sottovaluta proprio il gusto. Eppure di varietà, al mondo, ne esistono decine: arrivano da mari e montagne, sono grezze, raffinate, aromatizzate o affumicate."));
		articles.add(new Content().setTitle("L’arte della conservazione: saor, scapece e colatura").setDescription("L’acciuga e l’alice sono definite in maniera univoca come sinonimi da Linneo nel Systema Naturae per regna tria naturae, secundum classes, ordines, genera, species, cum characteribus, differentiis, synonymis, locis (1758 – fonte F.a.o). Siccome noi italiani siamo litigiosi, mi affido a un studioso inglese per fare il catalogo nelle varie lingue e ricette del mediterraneo."));
		articles.add(new Content().setTitle("Cibo e longevità: i segreti per vivere più a lungo").setDescription("Per vivere a lungo bisogna mangiar bene. È questo il mantra, spesso e volentieri tediante, che ci si sente ripetere come un martello pneumatico da qualche anno a questa parte. Non vi è medico, dietologo, programma televisivo, rivista o condivisione social che non ne faccia menzione. "));
		articles.add(new Content().setTitle("Napoli: dove mangiare vicino alla strada dei Presepi").setDescription("San Gregorio Armeno è nota in tutto il mondo come la strada dei Presepi. A Napoli in questo periodo viene presa d’assalto da milioni di turisti e anche dagli stessi napoletani che almeno una volta l’anno, come da tradizione, si recano in religioso pellegrinaggio ad ammirare l’arte presepiale dei maestri artigiani. "));
		contents.put(COOKING_CATEGORY, articles);
	}
	
	public static List<Content> getContentsByCategory(String category)
	{
		return contents.get(category);
	}
	
	public static String getCategoryByIndex(int index)
	{
		return CATEGORY_ORDER[index];
	}
	
	public static int getCatoriesCount()
	{
		return contents.entrySet().size();
	}
	
}

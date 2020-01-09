import org.jgrapht.*;
import org.jgrapht.graph.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;

import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import com.mxgraph.util.mxCellRenderer;

import org.jgrapht.ext.*;
import org.jgrapht.alg.scoring.*;
import org.jgrapht.alg.shortestpath.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Graphs extends JApplet {

	private static final long serialVersionUID = 2202072534703043194L;

	private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

	private static Graph<String, DefaultWeightedEdge> g;

	//constructeur
	public Graphs() {
	}

	public static Graph<String, DefaultWeightedEdge> getG() {
		return g;
	}

	public static void setG(Graph<String, DefaultWeightedEdge> g) {
		Graphs.g = g;
	}

	//méthode pour construire le graphe
	public Graph<String, DefaultWeightedEdge> build(HashSet<Tweets> collTweets){
		// initialisation d'un graphe pondéré et orienté
		g = new DefaultDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);

		// ajout des sommets et des arcs
		Iterator<Tweets> iter=collTweets.iterator();
		while(iter.hasNext())
		{
			Tweets n = iter.next();
			String nom = n.getIdTwitto();
			String nomRt = n.getRtid();

			//test si le twitto est deja dans la liste de sommets
			if(g.containsVertex(nom)==false) {
				//ajout d'un sommet
				g.addVertex(nom);
				//test si le tweet a été retweeté
				if(nomRt.length()!=0) {
					//test si le nom du twitto qui a RT est deja dans la liste de sommets
					if(g.containsVertex(nomRt)==false) {
						g.addVertex(nomRt);
						//ajout d'un arc : nom = source et nomRt = cible
						g.addEdge(nom, nomRt);
						//poids fixé à 1
						g.setEdgeWeight(nom, nomRt, 1);
					} else {
						//test si l'arc existe deja
						if(g.containsEdge(nom, nomRt)) {
							//alors on lui rajoute 1 à son poids
							g.setEdgeWeight(nom, nomRt, g.getEdgeWeight(g.getEdge(nom, nomRt)) +1);
						} else {
							//sinon on l'ajoute avec un poids de 1
							g.addEdge(nom, nomRt);
							g.setEdgeWeight(nom, nomRt, 1);
						}
					}
				}
			}
		}		
		return g;
	}

	//méthode pour afficher le graphe
	public void showGraph(){
		//initialisation du Listenable graph
		ListenableGraph<String, DefaultWeightedEdge> bg = new DefaultListenableGraph<>(new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class));

		//copie du graphe g dans le Listenable graphe bg pour pouvoir l'afficher
		//liste des arcs du graphe
		Set<DefaultWeightedEdge> edgeSet = g.edgeSet();
		//pour chaque arc
		for (DefaultWeightedEdge e : edgeSet) {
			//int deg = g.degreeOf(g.getEdgeTarget(e));
			//if(deg>8500) {
			//recuperation des sommets source
			String v1 = g.getEdgeSource(e);

			//et destination
			String v2 = g.getEdgeTarget(e);

			//ajout des sommets
			bg.addVertex(v1);
			bg.addVertex(v2);

			//récuperation des poids
			double w = g.getEdgeWeight(e);
			//ajout des arcs
			DefaultWeightedEdge edge = bg.addEdge(v1, v2);
			//et des poids
			bg.setEdgeWeight(edge, w);
			//}
		}

		//code inspiré de la demo
		//https://github.com/jgrapht/jgrapht/releases/tag/jgrapht-1.3.0/jgrapht-demo/src/main/java/org/jgrapht/demo
		// creation d'une visualisation avec JGRAPH, via un adaptateur
		JGraphXAdapter<String, DefaultWeightedEdge> jgxAdapter = new JGraphXAdapter<String, DefaultWeightedEdge>(bg);

		setPreferredSize(DEFAULT_SIZE);
		mxGraphComponent component = new mxGraphComponent(jgxAdapter);
		component.setConnectable(false);
		component.getGraph().setAllowDanglingEdges(false);
		getContentPane().add(component);
		resize(DEFAULT_SIZE);

		mxCircleLayout layout = new mxCircleLayout(jgxAdapter);

		// centrer le cercle
		int radius = 100;
		layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
		layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
		layout.setRadius(radius);
		layout.setMoveCircle(true);

		layout.execute(jgxAdapter.getDefaultParent());

		//enregistrement dans une image
		BufferedImage image =  mxCellRenderer.createBufferedImage(jgxAdapter, null, 2, Color.WHITE, true, null);
		//Affectation à l'objet imgFile le nom en sortie qui sera ImgGraphe
		File imgFile = new File("ImgGraphe.png");
		try {
			//ecriture de l'image au format png
			ImageIO.write(image, "PNG", imgFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public double[] statistique() {
		//ordre = nombre de sommets
		int ordre = g.vertexSet().size();
		//volume = nombre d'arcs
		int volume = g.edgeSet().size();
		//instanciation de l'objet GraphMeasurer
		GraphMeasurer <String, DefaultWeightedEdge> graphMeasurer = new GraphMeasurer <String, DefaultWeightedEdge>(g);
		//calcul du diametre du graphe
		double diametre = graphMeasurer.getDiameter();
		double[] res = {ordre,volume,diametre};
		return res;
	}

	public List<Entry<String, Double>> pageRank(){
		//instanciation de l'objet Page Rank
		PageRank <String, DefaultWeightedEdge> pageRank = new PageRank <String, DefaultWeightedEdge>(g);
		//getScores renvoie une map composé du nom du twitto et de son score
		Map<String,Double> top = pageRank.getScores();
		//on veut le top 3
		int n = 3;
		List<Entry<String, Double>> greatest = findGreatest(top, n);
		return greatest;
	}

	//code source de la fonction :
	//https://stackoverflow.com/questions/21465821/how-to-get-5-highest-values-from-a-hashmap
	private static <K, V extends Comparable<? super V>> List<Entry<K, V>> 
	findGreatest(Map<K, V> map, int n)
	{
		Comparator<? super Entry<K, V>> comparator = 
				new Comparator<Entry<K, V>>()
		{
			@Override
			public int compare(Entry<K, V> e0, Entry<K, V> e1)
			{
				V v0 = e0.getValue();
				V v1 = e1.getValue();
				return v0.compareTo(v1);
			}
		};
		PriorityQueue<Entry<K, V>> highest = 
				new PriorityQueue<Entry<K,V>>(n, comparator);
		for (Entry<K, V> entry : map.entrySet())
		{
			highest.offer(entry);
			while (highest.size() > n)
			{
				highest.poll();
			}
		}

		List<Entry<K, V>> result = new ArrayList<Map.Entry<K,V>>();
		while (highest.size() > 0)
		{
			result.add(highest.poll());
		}
		return result;
	}

}

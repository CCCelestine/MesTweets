import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.io.*;
import org.jgrapht.traverse.*;

import java.io.*;
import java.net.*;
import java.util.*;

import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import com.mxgraph.util.mxCellRenderer;

import org.jgrapht.ext.*;
import org.jgrapht.alg.interfaces.*;
import org.jgrapht.alg.scoring.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

//https://github.com/jgrapht/jgrapht/releases/tag/jgrapht-1.3.0/jgrapht-demo/src/main/java/org/jgrapht/demo

public class testjGraphT extends JApplet {

	private static final long serialVersionUID = 2202072534703043194L;

	private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

	private JGraphXAdapter<String, DefaultEdge> jgxAdapter;

	protected HashSet<Tweets> collTweets;

	public testjGraphT(HashSet<Tweets> collTweets) {
		setCollTweets(collTweets);
	}

	public HashSet<Tweets> getCollTweets() {
		return collTweets;
	}

	public void setCollTweets(HashSet<Tweets> collTweets) {
		this.collTweets = collTweets;
	}

	public void creation() {//ne pas afficher sommet seul + que ceux avec un grand degre
		ListenableGraph<String, DefaultEdge> g = new DefaultListenableGraph<>(new DefaultDirectedWeightedGraph<>(DefaultEdge.class));

		// create a visualization using JGraph, via an adapter
		jgxAdapter = new JGraphXAdapter<>(g);

		setPreferredSize(DEFAULT_SIZE);
		mxGraphComponent component = new mxGraphComponent(jgxAdapter);
		component.setConnectable(false);
		component.getGraph().setAllowDanglingEdges(false);
		getContentPane().add(component);
		resize(DEFAULT_SIZE);

		Iterator<Tweets> iter=collTweets.iterator();
		ArrayList<String> myNumbers = new ArrayList<String>();
		int sommet=0;
		int ii=0;
		while(iter.hasNext()& ii<3500)
		{
			Boolean flag=true;
			Boolean flag2=true;
			Tweets n = iter.next();
			String nom = n.getIdTwitto();
			String nomRt = n.getRtid();
			ii++;
			for (String i : myNumbers) {
				if(nom.compareTo(i)==0) {
					flag=false;
					break;
				}
			}
			for (String i : myNumbers) {
				if(nomRt.compareTo(i)==0) { //on rajoute aussi celui qui rt
					flag2=false;
					break;
				}
			}

			if (n.getRtid()!="" ) {
				if (flag2==true) {//si sommet non existnt 
					myNumbers.add(nomRt); //ajout dans le tableau
					g.addVertex(nomRt);//creation du point
					sommet++;
				}
				if (flag==true) { //pareil poru le nom du celui qui tweet
					myNumbers.add(nom); 
					g.addVertex(nom);
					sommet++;
				}
				g.addEdge(nomRt, nom);//ajout de l'arrete 
			}

			//g.PageRank();
			//PageRank x = new PageRank(g,double dampingFactor);

			// positioning via jgraphx layouts
			mxCircleLayout layout = new mxCircleLayout(jgxAdapter);

			// center the circle
			int radius = 10;
			DEFAULT_SIZE.width =1500;
			DEFAULT_SIZE.height=750;
			layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
			layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
			layout.setRadius(radius);
			layout.setMoveCircle(true);

			layout.execute(jgxAdapter.getDefaultParent());



		}
	}


	public void saveImage(String filename)
	{
		ListenableGraph<String, DefaultEdge> jGraphT = new DefaultListenableGraph<>(new DefaultDirectedWeightedGraph<>(DefaultEdge.class));
		JGraphXAdapter<String, DefaultEdge> graphX = new JGraphXAdapter<String, DefaultEdge>(jGraphT);
		mxIGraphLayout layout = new mxCircleLayout(graphX);
		layout.execute(graphX.getDefaultParent());
		BufferedImage image =  mxCellRenderer.createBufferedImage(graphX, null, 2, Color.WHITE, true, null);
		File imgFile = new File(filename);
		try {
			ImageIO.write(image, "PNG", imgFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}

	public void init()
	{
		ListenableGraph<String, DefaultEdge> g = new DefaultListenableGraph<>(new DefaultDirectedWeightedGraph<>(DefaultEdge.class));

		// create a visualization using JGraph, via an adapter
		jgxAdapter = new JGraphXAdapter<>(g);

		setPreferredSize(DEFAULT_SIZE);
		mxGraphComponent component = new mxGraphComponent(jgxAdapter);
		component.setConnectable(false);
		component.getGraph().setAllowDanglingEdges(false);
		getContentPane().add(component);
		resize(DEFAULT_SIZE);

		Iterator<Tweets> iter=collTweets.iterator();
		ArrayList<String> myNumbers = new ArrayList<String>();
		
		while(iter.hasNext())
		{
			Boolean flag=true;
			Boolean flag2=true;
			Tweets n = iter.next();
			String nom = n.getIdTwitto();
			String nomRt = n.getRtid();
			for (String i : myNumbers) {
				if(nom.compareTo(i)==0) {
					flag=false;
					break;
				}
			}

			if(flag==true) { //twitto
				g.addVertex(nom);
				myNumbers.add(nom);
				sommet++;
			}

			for (String i : myNumbers) {
				if(nomRt.compareTo(i)==0) { //on rajoute aussi celui qui rt
					flag2=false;
					break;
				}
			}
			if (flag2==true ) { //twitto qui rt
				g.addVertex(nomRt);
				myNumbers.add(nomRt);
				sommet++;
				//graph.addEdge(nomRt+"-"+nom, nomRt, nom);
			}
			if (n.getRtid()!="") {
				g.addEdge(nomRt, nom);
			}

			//g.PageRank();
			//PageRank x = new PageRank(g,double dampingFactor);

			// positioning via jgraphx layouts
			mxCircleLayout layout = new mxCircleLayout(jgxAdapter);

			// center the circle
			int radius = 100;
			layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
			layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
			layout.setRadius(radius);
			layout.setMoveCircle(true);

			layout.execute(jgxAdapter.getDefaultParent());
			// that's all there is to it!...
		}
	}
}



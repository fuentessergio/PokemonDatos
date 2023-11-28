package dao.pokemon;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAOXML implements PokemonDAO{
    private String archivoXML;
    private Document document;

    public PokemonDAOXML (String archivoXML) {
        this.archivoXML = archivoXML;
        try {
            File archivo = new File(archivoXML);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            if (!archivo.exists()){
                document = builder.newDocument(); // crealo si no existe
                Element raiz = document.createElement("pokemons");
                document.appendChild(raiz); // se agrega al documento el elemento raiz
                guardarXML();
            } else{
                document = builder.parse(archivo);
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean estaVacio() {
        NodeList pokemonNodes = document.getElementsByTagName("pokemon");
        if(pokemonNodes.getLength() == 0){
            return true; // esta vacio
        } else return false;
    }

    @Override
    public boolean estaLLeno() {
        return false;
    }

    @Override
    public void aniadir(Pokemon pokemon) throws NoMasPokemonsException, PokemonDuplicadoException {
        //comprobar si estalleno
        if(estaLLeno()){
            throw new NoMasPokemonsException();
        }
        //comprobar si pokemon repetido
        NodeList pokemonNodes = document.getElementsByTagName("pokemon");
        for (int i = 0; i < pokemonNodes.getLength(); i++) {
            Element pokemonElement = (Element) pokemonNodes.item(i);
            String nombre = pokemonElement.getElementsByTagName("nombre").item(0).getTextContent();
            if(nombre.equals(pokemon.getNombre())){
                throw new PokemonDuplicadoException();
            }
        }
        //crear el elemento pokemon a añadir
        Element pokemonElement = document.createElement("pokemon");

        //añadir los hijos con el metodo crearElemento
        pokemonElement.appendChild(crearElemento("nombre", pokemon.getNombre(),document));
        pokemonElement.appendChild(crearElemento("nivel", String.valueOf(pokemon.getNivel()),document));
        pokemonElement.appendChild(crearElemento("vida",String.valueOf(pokemon.getVida()),document));
        pokemonElement.appendChild(crearElemento("ataque",String.valueOf(pokemon.getAtaque()),document));
        pokemonElement.appendChild(crearElemento("defensa",String.valueOf(pokemon.getDefensa()),document));
        pokemonElement.appendChild(crearElemento("ataque_especial",String.valueOf(pokemon.getAtaqueEspecial()),document));
        pokemonElement.appendChild(crearElemento("defensa_especial",String.valueOf(pokemon.getDefensaEspecial()),document));
        pokemonElement.appendChild(crearElemento("velocidad",String.valueOf(pokemon.getVelocidad()),document));

        document.getDocumentElement().appendChild(pokemonElement);
        guardarXML();
    }

    @Override
    public boolean eliminar(Pokemon pokemon) {
        NodeList pokemonNodes = document.getElementsByTagName("pokemon");
        for (int i = 0; i < pokemonNodes.getLength(); i++) {
            Element pokemonElement = (Element) pokemonNodes.item(i);
            String nombre = pokemonElement.getElementsByTagName("nombre").item(0).getTextContent();
            if(nombre.equals(pokemon.getNombre())){
                System.out.println("Pokemon: " + nombre);
                pokemonElement.getParentNode().removeChild(pokemonElement);
                guardarXML();
                return true;
            }
        }
        return false;
    }

    @Override
    public void pokemonCSV(String ruta, String nombre, int nivel, int vida, int ataque, int defensa, int ataqueEspecial, int defensaEspecial, int velocidad) {

    }

    @Override
    public void imprimirPokemonCSV(String ruta) throws NoSuchFileException {

    }

    @Override
    public void imprimirPokemon(String nombre) {
        List<Pokemon> pokemons = leerPokemons();
        for (Pokemon p : pokemons) {
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                System.out.println(p);
                System.out.println();
            } else throw new RuntimeException();
        }
    }

    @Override
    public List<Pokemon> leerPokemons()  {
        List<Pokemon> pokemons = new ArrayList<>();
        NodeList pokemonNodes = document.getElementsByTagName("pokemon");
        for (int i = 0; i < pokemonNodes.getLength(); i++) {
            Element pokemonElement = (Element) pokemonNodes.item(i);
            try {
                String nombre = obtenerValor(pokemonElement,"nombre");
                gestionarPokemon(pokemons, pokemonElement, nombre);
            } catch (PokemonDuplicadoException e) {
                throw new RuntimeException(e);
            }
        }
        //System.out.println(pokemons);
        return pokemons;
    }

    @Override
    public List<Pokemon> leerPokemons(String nombreBuscado) {
        List<Pokemon> pokemons = new ArrayList<>();
        NodeList pokemonNodes = document.getElementsByTagName("pokemon");
        for (int i = 0; i < pokemonNodes.getLength(); i++) {
            Element pokemonElement = (Element) pokemonNodes.item(i);
            try {
                String nombre = obtenerValor(pokemonElement,"nombre");
                if(nombreBuscado.toLowerCase().contains(nombre.toLowerCase())){
                    gestionarPokemon(pokemons, pokemonElement, nombre);
                }
            } catch (PokemonDuplicadoException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(pokemons);
        return pokemons;
    }

    @Override
    public void actualizar(Pokemon p) {
        NodeList pokemonNodes = document.getElementsByTagName("pokemon");
        for (int i = 0; i < pokemonNodes.getLength(); i++) {
            Element pokemon = (Element) pokemonNodes.item(i);
            String nombre = obtenerValor(pokemon,"nombre");
            if(nombre.equals(p.getNombre())){
                //actualizar valores
                pokemon.getElementsByTagName("nivel").item(0).setTextContent(String.valueOf(p.getNivel()));
                pokemon.getElementsByTagName("vida").item(0).setTextContent(String.valueOf(p.getVida()));
                pokemon.getElementsByTagName("ataque").item(0).setTextContent(String.valueOf(p.getAtaque()));
                pokemon.getElementsByTagName("defensa").item(0).setTextContent(String.valueOf(p.getDefensa()));
                pokemon.getElementsByTagName("ataque_especial").item(0).setTextContent(String.valueOf(p.getAtaqueEspecial()));
                pokemon.getElementsByTagName("defensa_especial").item(0).setTextContent(String.valueOf(p.getDefensaEspecial()));
                pokemon.getElementsByTagName("velocidad").item(0).setTextContent(String.valueOf(p.getVelocidad()));

                // Guardar los cambios en el archivo XML
                guardarXML();
                break;
            }
        }
    }

    private void gestionarPokemon(List<Pokemon> pokemons, Element pokemonElement, String nombre) throws PokemonDuplicadoException {
        int nivel = Integer.parseInt(obtenerValor(pokemonElement,"nivel"));
        int vida = Integer.parseInt(obtenerValor(pokemonElement,"vida"));
        int ataque = Integer.parseInt(obtenerValor(pokemonElement,"ataque"));
        int defensa = Integer.parseInt(obtenerValor(pokemonElement,"defensa"));
        int ataqueEspecial = Integer.parseInt(obtenerValor(pokemonElement,"ataque_especial"));
        int defensaEspecial = Integer.parseInt(obtenerValor(pokemonElement,"defensa_especial"));
        int velocidad = Integer.parseInt(obtenerValor(pokemonElement,"velocidad"));

        Pokemon pokemon = new Pokemon(nombre,nivel,vida,ataque,defensa,ataqueEspecial,defensaEspecial,velocidad);
        pokemons.add(pokemon);
    }


    private String obtenerValor (Element padreElement, String elementName){
        //buscamos elementos con el nombre que se proporciona
        NodeList elements = padreElement.getElementsByTagName(elementName);

        if(elements.getLength()>0){
            // si hay algun elemento, cogemos el primero y accedo a su primer hijo
            Node elementNode = elements.item(0).getFirstChild();

            if(elementNode !=null){
                // Si no es nulo, obtenemos valor del nodo
                return elementNode.getNodeValue();
            }
        }
        throw new RuntimeException();
    }
    private void guardarXML(){
        try{
            TransformerFactory transformerFactory = TransformerFactory.newDefaultInstance();

            File xsltlimpiar = new File("limpiar.xslt");
            StreamSource limpiarStream = new StreamSource(xsltlimpiar);
            Transformer transformer = transformerFactory.newTransformer(limpiarStream);

            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(document);
            File archivo = new File(archivoXML);

            StreamResult result = new StreamResult(archivo);
            transformer.transform(source,result);

        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
    public static Element crearElemento (String nombre,String valor, Document document) {
        //nuevo elemento XML
        Element element = document.createElement(nombre);
        Text text = document.createTextNode(valor); // el valor que contiene el nuvo hijo
        element.appendChild(text); // contenido del XML para el nuevo elemento
        return element;
    }
}

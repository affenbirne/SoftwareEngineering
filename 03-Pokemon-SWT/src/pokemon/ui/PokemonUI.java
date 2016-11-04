package pokemon.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import pokemon.data.Pokemon;

/**
 * Pokemon UIDialog displays Pokemons in SWT Table Widget
 *
 */
public class PokemonUI extends Dialog {

	private ArrayList<Pokemon> pokemons;
	/**
	 * @param parent
	 * @param pokemons
	 */
	public PokemonUI(Shell parent, ArrayList<Pokemon> pokemons) {
		// Pass the default styles here
		this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL, pokemons);
	}

	/**
	 * @param parent
	 * @param style
	 * @param pokemons
	 */
	public PokemonUI(Shell parent, int style, ArrayList<Pokemon> pokemons) {
		// Let users override the default styles
		super(parent, style);
		setText("Pokemon Manager");
		setPokemons(pokemons);
	}

	/**
	 * Opens the dialog
	 */
	public void open() {
		// Create the dialog window
		Shell shell = new Shell(getParent(), getStyle());
		shell.setText(getText());
		createContents(shell);
		shell.pack();
		shell.open();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public ArrayList<Pokemon> getPokemons() {
		return pokemons;
	}

	@SuppressWarnings("unchecked")
	public void setPokemons(ArrayList<Pokemon> pokemons) {
		// Clone Pokemon list so it can be sorted later
		this.pokemons = (ArrayList<Pokemon>) pokemons.clone();
	}

	/**
	 * Creates the dialog's contents
	 * 
	 * @param shell
	 *            the dialog window
	 */
	private void createContents(final Shell shell) {
		GridLayout gl = new GridLayout();
		gl.numColumns = 1;
		shell.setLayout(gl);
		shell.setText("Pokemon Manager");

		Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		// perform general setup of the Table 
		GridData gd = new GridData(GridData.FILL_BOTH);
		table.setLayoutData(gd);
		table.setHeaderVisible(true);

		// create table headers using TableColumn and Listeners
		Listener sortListener = new Listener() {
			public void handleEvent(Event e) {
				TableColumn col = (TableColumn) e.widget;
				table.setSortColumn(col);
				// Sort by column
				switch (col.getText()) {
				case "Number":
					Collections.sort(pokemons, Pokemon.compareByNumber);
					break;
				case "Name":
					Collections.sort(pokemons, Pokemon.compareByName);
					break;
				case "Type":
					Collections.sort(pokemons, Pokemon.compareByType);
					break;
				case "Trainer":
					Collections.sort(pokemons, Pokemon.compareByTrainer);
					break;
				case "Swaps":
					Collections.sort(pokemons, Pokemon.compareBySwaps);
					break;
				case "SwapAllow":
					Collections.sort(pokemons, Pokemon.compareBySwapAllow);
					break;
				case "Competitions":
					Collections.sort(pokemons, Pokemon.compareByCompetitions);
					break;
				}
				table.removeAll();
				fillTable(table);
				table.update();
			}
		};
		List<String> titles = getTableHeaders();
		Iterator<String> it = titles.iterator();
		while (it.hasNext()) {
			String title = it.next();
			TableColumn col = new TableColumn(table, SWT.NULL);
			col.setText(title);
			col.addListener(SWT.Selection, sortListener);
		}
		
		// create table rows using TableItem, each row of the table is one Pokemon  
		fillTable(table);
		
		// auto size columns
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(i).pack();
		}
		shell.pack();
	}

	private void fillTable(Table table) {
		Iterator<Pokemon> it = pokemons.iterator();
		while (it.hasNext()) {
			Pokemon poke = it.next();
			TableItem item = new TableItem(table, SWT.NULL);
			item.setText(new String[] {""+poke.getNumber(),
					poke.getName(),
					""+poke.getType(),
					poke.getTrainer().getFirstname() + " "+ poke.getTrainer().getLastname(),
					""+poke.getSwaps().size(),
					""+poke.isSwapAllow(),
					""+poke.getCompetitions().size()});
		}
	}

	/**
	 * Create table headers String
	 * 
	 * @return
	 */
	private List<String> getTableHeaders() {
		List<String> ret = new ArrayList<String>();
		ret.add("Number");
		ret.add("Name");
		ret.add("Type");
		ret.add("Trainer");
		ret.add("Swaps");
		ret.add("SwapAllow");
		ret.add("Competitions");
		return ret;
	}

}
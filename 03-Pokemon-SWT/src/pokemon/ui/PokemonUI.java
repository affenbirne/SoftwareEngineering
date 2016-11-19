package pokemon.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import pokemon.Competition;
import pokemon.PokemonManager;
import pokemon.Swap;
import pokemon.data.Pokemon;
import pokemon.data.Trainer;
import pokemon.data.Type;

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
	 * @param shell the dialog window
	 */
	private void createContents(final Shell shell) {
		GridLayout gl = new GridLayout();
		gl.numColumns = 1;
		shell.setLayout(gl);
		shell.setText("Pokemon Manager");

		Table table = new Table(shell, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
		// perform general setup of the Table 
		GridData gd = new GridData(GridData.FILL_BOTH);
		table.setLayoutData(gd);
		table.setHeaderVisible(true);

		List<String> titles = getTableHeaders();
		Iterator<String> it = titles.iterator();
		while (it.hasNext()) {
			String title = it.next();
			TableColumn col = new TableColumn(table, SWT.NULL);
			col.setText(title);
			col.addListener(SWT.Selection, this.sortListener);
		}
		
		// create table rows using TableItem, each row of the table is one Pokemon  
		fillTable(table);
		table.addListener(SWT.MouseDoubleClick, editListener);
		
		// create context menu
		Menu ctxt = new Menu(table);
		MenuItem ctxtCreate = new MenuItem(ctxt, SWT.CASCADE);
		ctxtCreate.setText("Create Pokemon");
		ctxtCreate.addListener(SWT.Arm, this.createPokemon(table));
		ctxtCreate.setMenu(new Menu(ctxt));
		
		MenuItem ctxtSwap = new MenuItem(ctxt, SWT.CASCADE);
		ctxtSwap.setText("Swap with");
		ctxtSwap.addListener(SWT.Arm, this.swapPokemon(table, shell));
		ctxtSwap.setMenu(new Menu(ctxt));
		
		MenuItem ctxtCompetition = new MenuItem(ctxt, SWT.CASCADE);
		ctxtCompetition.setText("Compete with");
		ctxtCompetition.addListener(SWT.Arm, this.competePokemon(table, shell));
		ctxtCompetition.setMenu(new Menu(ctxt));
		
		MenuItem ctxtDelete = new MenuItem(ctxt, SWT.PUSH);
		ctxtDelete.setText("Delete Pokemon");
		ctxtDelete.addListener(SWT.Selection, this.deletePokemon(table, shell));
		
		table.setMenu(ctxt);
		
		// Gray out menu items when no Pokemon selected
		table.addListener(SWT.MouseUp, new Listener() {
			public void handleEvent(Event e) {
				if (e.button == 3) {
					if (table.getSelection().length != 1) {
						ctxtSwap.setEnabled(false);
						ctxtCompetition.setEnabled(false);
						ctxtDelete.setEnabled(false);	
					} else {
						// only Activate for pokemons with trainers
						if (table.getSelection()[0].getData() instanceof Pokemon &&
								((Pokemon)table.getSelection()[0].getData()).getTrainer() != null) {
							if (((Pokemon)table.getSelection()[0].getData()).isSwapAllow()) {
								ctxtSwap.setEnabled(true);
							} else {
								ctxtSwap.setEnabled(false);
							}
							ctxtCompetition.setEnabled(true);	
						}
						else {
							ctxtSwap.setEnabled(false);
							ctxtCompetition.setEnabled(false);
						}
						ctxtDelete.setEnabled(true);
					}
				}
			}
		});
		
		// auto size columns
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(i).pack();
		}
		shell.pack();
	}

	private void fillTable(Table table) {
		table.removeAll();
		pokemons = PokemonManager.getPokemons();
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
			item.setData(poke);
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

	private Listener createPokemon(Table table) {
		return new Listener() {
			public void handleEvent(Event e) {
				MenuItem mi = (MenuItem) e.widget;
				Menu menu = mi.getMenu();
				emptyMenu(menu);
				for (Trainer tr : Trainer.getTrainers()) {
					MenuItem trmi = new MenuItem(menu, SWT.CASCADE);
					trmi.setText(tr.toString());
					Menu trmenu = new Menu(menu);
					trmi.setMenu(trmenu);
					trmi.addListener(SWT.Arm, new Listener() {
						public void handleEvent(Event e) {
							emptyMenu(trmenu);
							for (Type t : Type.values()) {
								MenuItem tpmi = new MenuItem(trmenu, SWT.PUSH);
								tpmi.setText(t.toString());
								tpmi.addSelectionListener(new SelectionAdapter() {
									@Override
									public void widgetSelected(final SelectionEvent e) {
										Pokemon poke = new Pokemon("New Pokemon", t);
										poke.setTrainer(tr);
										PokemonManager.addPokemon(poke);
										PokemonManager.storePokemons();
										fillTable(table);
									}
								});
							}
						}
					});
				}
			}
		};
	}

	private Listener swapPokemon(Table table, Shell shell) {
		return new Listener() {
			public void handleEvent(Event e) {
				if (table.getSelection().length > 0 && table.getSelection()[0] != null
						&& table.getSelection()[0].getData() instanceof Pokemon) {
					Pokemon p1 = (Pokemon) table.getSelection()[0].getData();
					MenuItem mi = (MenuItem) e.widget;
					Menu menu = mi.getMenu();
					emptyMenu(menu);
					
					for (Pokemon p2 : pokemons) {
						if (p1 == p2 || !p2.isSwapAllow())
							continue;
						MenuItem pkmi = new MenuItem(menu, SWT.PUSH);
						pkmi.setText(p2.toString());
						pkmi.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(final SelectionEvent e) {
								MessageBox msg = new MessageBox(shell,
										SWT.ICON_QUESTION | SWT.YES | SWT.NO);
								msg.setText("Swap Pokemon?");
								msg.setMessage("Are you sure you want to swap the Pokemons:\n"
										+ p1.toString() + "\n" + p2.toString());
								if (msg.open() == SWT.YES) {
									Competition comp = new Competition();
									comp.execute(p1, p2);
									PokemonManager.storePokemons();
									fillTable(table);
								}
							}
						});
					}
				}
			}
		};
	}

	private Listener competePokemon(Table table, Shell shell) {
		return new Listener() {
			public void handleEvent(Event e) {
				if (table.getSelection().length > 0 && table.getSelection()[0] != null
						&& table.getSelection()[0].getData() instanceof Pokemon) {
					Pokemon p1 = (Pokemon) table.getSelection()[0].getData();
					MenuItem mi = (MenuItem) e.widget;
					Menu menu = mi.getMenu();
					emptyMenu(menu);
					
					for (Pokemon p2 : pokemons) {
						if (p1 == p2 || p2.getTrainer() != null)
							continue;
						MenuItem pkmi = new MenuItem(menu, SWT.PUSH);
						pkmi.setText(p2.toString());
						pkmi.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(final SelectionEvent e) {
								MessageBox msg = new MessageBox(shell,
										SWT.ICON_QUESTION | SWT.YES | SWT.NO);
								msg.setText("Let Pokemon compete?");
								msg.setMessage(p1.toString() + "\n    VERSUS\n" + p2.toString());
								if (msg.open() == SWT.YES) {
									Swap swap = new Swap();
									swap.execute(p1, p2);
									PokemonManager.storePokemons();
									fillTable(table);
								}
							}
						});
					}
				}
			}
		};
	}
	
	private Listener deletePokemon(Table table, Shell shell) {
		return new Listener() {
			public void handleEvent(Event e) {
				if (table.getSelection().length > 0 && table.getSelection()[0] != null
						&& table.getSelection()[0].getData() instanceof Pokemon) {
					Pokemon poke = (Pokemon) table.getSelection()[0].getData();
					MessageBox msg = new MessageBox(shell,
							SWT.ICON_QUESTION | SWT.YES | SWT.NO);
					msg.setText("Delete Pokemon?");
					msg.setMessage("Are you sure you want to delete the Pokemon:\n"
							+ poke.toString());
					if (msg.open() == SWT.YES) {
						PokemonManager.getPokemons().remove(poke);
						pokemons.remove(poke);
						poke = null;
						PokemonManager.storePokemons();
						fillTable(table);
					}
				}
				
			}
		};
	}
	
	// create table headers using TableColumn and Listeners
	private final Listener sortListener = new Listener() {
		public void handleEvent(Event e) {
			TableColumn col = (TableColumn) e.widget;
			col.getParent().setSortColumn(col);
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
			fillTable(col.getParent());
			col.getParent().update();
		}
	};
	
	// Table editor listener
	private final Listener editListener = new Listener() {
		public void handleEvent(Event e) {
			Table table = (Table) e.widget;
			TableEditor tedit = new TableEditor(table);
			tedit.horizontalAlignment = SWT.CENTER;
			tedit.grabHorizontal = true;
			
			Point pt = new Point(e.x, e.y);
			TableItem item = table.getItem(pt);
			if (item == null) {
				return;
			}
			int col;
			for (col = 0; col < table.getColumnCount(); col++) {
				Rectangle rect = item.getBounds(col);
				if (rect.contains(pt)) {
					break;
				}
			}
			// Depending on which column different editors
			switch (col) {
			case 0:		// __Number__
				// !!! Not editable
				break;
			case 1:		// __Name__
				Text text = new Text(table, SWT.NONE);
				text.addListener(SWT.FocusOut | SWT.Traverse,
						this.getNameListener(item));
				tedit.setEditor(text, item, col);
				text.setText(item.getText(col));
				text.selectAll();
				text.setFocus();
				break;
			case 2:		// __Type__
				CCombo combo_type = new CCombo(table, SWT.NONE);
				combo_type.addSelectionListener(this.getTypeListener(item));
				combo_type.addFocusListener(this.getDefaultListener());
				for (Type t : Type.values()) {
					combo_type.add(t.name());
				}
				combo_type.setText(item.getText(col));
				tedit.setEditor(combo_type, item, col);
				combo_type.setFocus();
				break;
			case 3:		// __Trainer__
				/* !!! Not editable
				 * CCombo combo_trainer = new CCombo(table, SWT.NONE);
				 * combo_trainer.addSelectionListener(this.getTrainerListener(item));
				 * combo_trainer.addFocusListener(this.getDefaultListener());
				 * for (Trainer tr : Trainer.getTrainers()) {
				 * 	combo_trainer.add(tr.toString());
				 * }
				 * combo_trainer.setText(item.getText(col));
				 * tedit.setEditor(combo_trainer, item, col);
				 * combo_trainer.setFocus();
				 */
				break;
			case 4:		// __Swaps__
				// !!! Not editable
				break;
			case 5:		// __SwapAllow__
				Button checkbox = new Button(table,SWT.CHECK);
				boolean swapAllow = Boolean.valueOf(item.getText(col));
				checkbox.setSelection(swapAllow);
				checkbox.addSelectionListener(this.getSwapListener(item));
				checkbox.addFocusListener(this.getDefaultListener());
				tedit.setEditor(checkbox, item, col);
				checkbox.setFocus();
				break;
			case 6:		// __Competitions__
				// !!! Not editable
				break;
			}
			table.update();
		}
		
		private FocusListener getDefaultListener() {
			return new FocusListener() {
				@Override
				public void focusLost(final FocusEvent e) {
					e.widget.dispose();
				}

				@Override
				public void focusGained(FocusEvent arg0) {
				};
			};
		}

		private Listener getNameListener(TableItem item) {
			return new Listener() {
				public void handleEvent(final Event e) {
					Text text = (Text) e.widget;
					if (e.type == SWT.FocusOut || e.detail == SWT.TRAVERSE_RETURN) {
						if (item.getData() != null && item.getData() instanceof Pokemon)
							((Pokemon)item.getData()).setName(text.getText());
						item.setText(1, text.getText());
						PokemonManager.storePokemons();
						text.dispose();
					}
					else if (e.detail == SWT.TRAVERSE_ESCAPE) {
						text.dispose();
						e.doit = false;
					}
				};
			};
		}
		
		private SelectionAdapter getTypeListener(TableItem item) {
			return new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent e) {
					CCombo combo = (CCombo) e.widget;
					Type t = Type.valueOf(combo.getText());
					if (item.getData() != null && item.getData() instanceof Pokemon) {
						((Pokemon) item.getData()).setType(t);
					}
					item.setText(2, t.name());
					PokemonManager.storePokemons();
					combo.dispose();
				}
			};
		}
		
		/* Not needed, cause not editable
		private SelectionAdapter getTrainerListener(TableItem item) {
			return new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent e) {
					CCombo combo = (CCombo) e.widget;
					Trainer tr = Trainer.getTrainers().get(combo.getSelectionIndex());
					if (item.getData() != null && item.getData() instanceof Pokemon) {
						((Pokemon) item.getData()).setTrainer(tr);
					}
					item.setText(3, tr.toString());
					PokemonManager.storePokemons();
					combo.dispose();
				}
			};
		}*/
		
		private SelectionAdapter getSwapListener(TableItem item) {
			return new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent e) {
					Button checkbox = (Button) e.widget;
					boolean swapAllow = checkbox.getSelection();
					if (item.getData() != null && item.getData() instanceof Pokemon) {
						((Pokemon) item.getData()).setSwapAllow(swapAllow);
					}
					item.setText(5, ""+swapAllow);
					PokemonManager.storePokemons();
					checkbox.dispose();
				}
			};
		}
		
	};
	
	public static void emptyMenu(Menu menu) {
		for (MenuItem mi : menu.getItems()) {
			mi.dispose();
		}
	}
}
package view;

import model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;

public class TableView extends JPanel {
	private JottoModel model;
	private String colName[] = {"Words","Exact","Partial"};
	private DefaultTableModel tableModel = new DefaultTableModel(colName,10);
	private JTable table = new JTable(tableModel);
	private JScrollPane scrlPane = new JScrollPane(table);
	private int row;
	private boolean firstCalled;
	
	public TableView(JottoModel aModel) {
		super();
		this.model = aModel;
		this.row = 0;
		this.firstCalled = true;
		
		this.layoutView();
		
		this.model.addView(new IView() {
			public void updateView() {
				updateViewHandler();
			}
		});
	}
	
	private void updateViewHandler(){
		if(this.firstCalled){
			this.firstCalled = false;
		}
		else{
			table.setValueAt(model.getGuessWord(), row, 0);
			table.setValueAt(model.getExact(), row, 1);
			table.setValueAt(model.getPartial(), row, 2);
			row++;
		}
	}
	
	private void layoutView() {
		table.setPreferredScrollableViewportSize(new Dimension(355,160));
		table.setEnabled(false);
		this.add(scrlPane);
	}
}

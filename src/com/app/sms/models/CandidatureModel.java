package com.app.sms.models;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class CandidatureModel extends AbstractTableModel {

	private String[] nomColumns = {"Id", "Nom", "Prenom", "Genre", "Telephone", "Email", "State", "Commentaires", "PicturePath","Enseignant"};
	private Vector<String[]> rows = new Vector<>();
	
	@Override
	public int getColumnCount() {
		return nomColumns.length;
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return rows.get(rowIndex)[columnIndex];
	}
	
	@Override
	public String getColumnName(int column) {
		return nomColumns[column];
	}
	
	public void loadData (List<Candidature> candidatures) {
		rows.clear();
		for (Candidature candidature : candidatures ){
			rows.add( new String [] {
					String.valueOf(candidature.getId()), 
					candidature.getNom(), 
					candidature.getPrenom(),
					candidature.getGenre(),
					candidature.getTelephone(),
					candidature.getEmail(),
					candidature.getState(),
					candidature.getCommentaires(),
					candidature.getPicturePath(),
					candidature.getEnseignant()
					}) ;
		}
		fireTableDataChanged();
	}

	public void clear() {
		rows.clear();
		fireTableDataChanged();
	}
}
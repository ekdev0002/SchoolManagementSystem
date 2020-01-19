package com.app.sms.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sms.dao.impl.NoteDao;
import com.app.sms.exceptions.AlreadyExistDataException;
import com.app.sms.exceptions.NotFoundDataException;

public class Note {

	private int id;
	private int idDevoirs;
	private double note;	
	private int coef;
	private int idEleve;
	
	public Note(int id, int idDevoirs, double note, int coef, int idEleve) {
		this(idDevoirs, note,coef,idEleve);
		this.id = id;
	}

	public Note(int idDevoirs, double note, int coef, int idEleve) {
		this.idDevoirs = idDevoirs;
		this.note = note;
		this.coef = coef;
		this.idEleve = idEleve;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdDevoirs() {
		return idDevoirs;
	}

	public void setIdDevoirs(int idDevoirs) {
		this.idDevoirs = idDevoirs;
	}

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}

	public int getCoef() {
		return coef;
	}

	public void setCoef(int coef) {
		this.coef = coef;
	}

	public int getIdEleve() {
		return idEleve;
	}

	public void setIdEleve(int idEleve) {
		this.idEleve = idEleve;
	}

	public void create() throws AlreadyExistDataException, SQLException {		
		
		NoteDao noteDao = new NoteDao ();
		
		if ( noteDao.find(this) )  {
			throw new AlreadyExistDataException ( "An error occured : Note already Exit !" ) ;
		} else {
			
			noteDao.create(this) ;
		}	
		
	}

	public void update() throws NotFoundDataException, SQLException {
		NoteDao noteDao = new NoteDao ();
		
		if (null != noteDao.find(id)) {
			noteDao.update(this);
		} else {
			throw new NotFoundDataException ( "An error occured : la note n'existe pas !" ) ;
		}
	}

	public void delete() throws NotFoundDataException, SQLException {
		NoteDao noteDao = new NoteDao ();
		
		if (null != noteDao.find(id)) {
			noteDao.delete(id);
		} else {
			throw new NotFoundDataException ( "An error occured : la note n'existe pas !" ) ;
		}
	}

	public static List<Note> list() throws SQLException {
		NoteDao noteDao = new NoteDao ();
		return noteDao.list();
	}
	
	public static List<Note> list(String idDevoirs) throws SQLException {
		NoteDao noteDao = new NoteDao ();
		return noteDao.list(idDevoirs);
	}
}

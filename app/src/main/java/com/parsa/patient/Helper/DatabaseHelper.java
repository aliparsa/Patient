package com.parsa.patient.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.parsa.patient.DataModel.ExtraField;
import com.parsa.patient.DataModel.Patient;
import com.parsa.patient.DataModel.Picture;
import com.parsa.patient.DataModel.Visit;

import java.util.ArrayList;

/**
 * Created by Ashkan on 1/4/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db.db";


    private static final String TABLE_PATIENT = "patient";
    private static final String TABLE_VISIT = "visit";
    private static final String TABLE_PICTURE = "picture";
    private static final String TABLE_EXTRAFIELD = "extrafield";







    //TABLE_PATIENT
    private static final String PATIENT_ID = "patient_id";
    private static final String PATIENT_NAME = "patient_name";
    private static final String PATIENT_DATEOFBIRTH = "patient_dateofbirth";
    private static final String PATIENT_NATIONALCODE = "patient_nationalcode";
    private static final String PATIENT_PHONENUMBER = "patient_phonenumber";
    private static final String PATIENT_ADDRESS = "patient_address";
    private static final String PATIENT_PROFILEPICTUREPATH = "patient_profilepicturepath";
    private static final String PATIENT_RISKFACTOR = "patient_riskfactor";
    private static final String PATIENT_DRUGS = "patient_drugs";
    private static final String PATIENT_PASTMEDICALHISTORY = "patient_pastmedicalhistory";
    private static final String PATIENT_PARACLINICANDPROCEDURE = "patient_paraclinicandprocedure";
    private static final String PATIENT_LAB = "patient_lab";


    //TABLE_VISIT
    private static final String VISIT_ID = "visit_id";
    private static final String VISIT_PATIENTID = "visit_patientid";
    private static final String VISIT_PROGRESSNOTE = "visit_progressnote";
    private static final String VISIT_PLAN = "visit_plan";


    //TABLE_PICTURE
    private static final String PICTURE_ID = "picture_id";
    private static final String PICTURE_PARENTID = "picture_parentid";
    private static final String PICTURE_PATH = "picture_path";


    //TABLE_EXTRAFIELD
    private static final String EXTRAFIELD_ID = "extrafield_id";
    private static final String EXTRAFIELD_TYPE = "extrafield_type";
    private static final String EXTRAFIELD_PARENTID = "extrafield_parentid";
    private static final String EXTRAFIELD_TITLE = "extrafield_title";
    private static final String EXTRAFIELD_VALUE = "extrafield_value";






    //constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        //put create queies here
        String CREATE_TABLE_PATIENT =
                "CREATE TABLE " + TABLE_PATIENT + "("
                        + PATIENT_ID + " INTEGER PRIMARY KEY, "
                        + PATIENT_NAME + " TEXT, "
                        + PATIENT_DATEOFBIRTH + " TEXT, "
                        + PATIENT_NATIONALCODE + " TEXT, "
                        + PATIENT_PHONENUMBER + " TEXT, "
                        + PATIENT_ADDRESS + " TEXT, "
                        + PATIENT_PROFILEPICTUREPATH + " TEXT, "
                        + PATIENT_RISKFACTOR + " TEXT, "
                        + PATIENT_DRUGS + " TEXT, "
                        + PATIENT_PASTMEDICALHISTORY + " TEXT, "
                        + PATIENT_PARACLINICANDPROCEDURE + " TEXT, "
                        + PATIENT_LAB + " TEXT"
                        + ")";
        db.execSQL(CREATE_TABLE_PATIENT);

        //visit
        String CREATE_TABLE_VISIT =
                "CREATE TABLE " + TABLE_VISIT + "("
                        + VISIT_ID + " INTEGER PRIMARY KEY, "
                        + VISIT_PATIENTID + " INTEGER, "
                        + VISIT_PROGRESSNOTE + " TEXT, "
                        + VISIT_PLAN + " TEXT"
                        + ")";
        db.execSQL(CREATE_TABLE_VISIT);

        //picture
        String CREATE_TABLE_PICTURE =
                "CREATE TABLE " + TABLE_PICTURE + "("
                        + PICTURE_ID + " INTEGER PRIMARY KEY, "
                        + PICTURE_PARENTID + " INTEGER, "
                        + PICTURE_PATH + " TEXT"
                        + ")";
        db.execSQL(CREATE_TABLE_PICTURE);

        //extra field
        String CREATE_TABLE_EXTRAFIELD =
                "CREATE TABLE " + TABLE_EXTRAFIELD + "("
                        + EXTRAFIELD_ID + " INTEGER PRIMARY KEY, "
                        + EXTRAFIELD_TYPE + " INTEGER, "
                        + EXTRAFIELD_PARENTID + " INTEGER, "
                        + EXTRAFIELD_TITLE + " TEXT, "
                        + EXTRAFIELD_VALUE + " TEXT"
                        + ")";
        db.execSQL(CREATE_TABLE_EXTRAFIELD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        //for other versions
    }

    //put other functions here

    public ArrayList<Patient> getAllPatients() {
        SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PATIENT, null);
        ArrayList<Patient> patients = new ArrayList<Patient>();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(PATIENT_ID));
                    String name = cursor.getString(cursor.getColumnIndex(PATIENT_NAME));
                    String dateofbirth = cursor.getString(cursor.getColumnIndex(PATIENT_DATEOFBIRTH));
                    String nationalcode = cursor.getString(cursor.getColumnIndex(PATIENT_NATIONALCODE));
                    String phonenumber = cursor.getString(cursor.getColumnIndex(PATIENT_PHONENUMBER));
                    String address = cursor.getString(cursor.getColumnIndex(PATIENT_ADDRESS));
                    String profilepicturepath = cursor.getString(cursor.getColumnIndex(PATIENT_PROFILEPICTUREPATH));
                    String riskfactor = cursor.getString(cursor.getColumnIndex(PATIENT_RISKFACTOR));
                    String drugs = cursor.getString(cursor.getColumnIndex(PATIENT_DRUGS));
                    String pastmedicalhistory = cursor.getString(cursor.getColumnIndex(PATIENT_PASTMEDICALHISTORY));
                    String paraclinicandprocedure = cursor.getString(cursor.getColumnIndex(PATIENT_PARACLINICANDPROCEDURE));
                    String lab = cursor.getString(cursor.getColumnIndex(PATIENT_LAB));

                    Patient patient = new Patient();
                    patient.setId(id);
                    patient.setName(name);
                    patient.setDateOfBirth(dateofbirth);
                    patient.setNationalCode(nationalcode);
                    patient.setPhoneNumber(phonenumber);
                    patient.setAddress(address);
                    patient.setProfilePicturePath(profilepicturepath);
                    patient.setRiskFactor(riskfactor);
                    patient.setDrugs(drugs);
                    patient.setPastMedicalHistory(pastmedicalhistory);
                    patient.setParaClinicAndProcedure(paraclinicandprocedure);
                    patient.setLab(lab);

                    patient.setExtraFields(getAllExtraFieldsOfPatient(patient));

                    patients.add(patient);
                } while (cursor.moveToNext());
            }
        }

        return patients;
    }


    public void insertPatient(Patient patient) {
        ContentValues values = new ContentValues();
        values.put(PATIENT_NAME, patient.getName());
        values.put(PATIENT_DATEOFBIRTH, patient.getDateOfBirth());
        values.put(PATIENT_NATIONALCODE, patient.getNationalCode());
        values.put(PATIENT_PHONENUMBER, patient.getPhoneNumber());
        values.put(PATIENT_ADDRESS, patient.getAddress());
        values.put(PATIENT_PROFILEPICTUREPATH, patient.getProfilePicturePath());
        values.put(PATIENT_RISKFACTOR, patient.getRiskFactor());
        values.put(PATIENT_DRUGS, patient.getDrugs());
        values.put(PATIENT_PASTMEDICALHISTORY, patient.getPastMedicalHistory());
        values.put(PATIENT_PARACLINICANDPROCEDURE, patient.getParaClinicAndProcedure());
        values.put(PATIENT_LAB, patient.getLab());
        this.getWritableDatabase().insert(TABLE_PATIENT, null, values);
    }


    public void updatePatient(Patient patient) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_PATIENT + " SET "
                + PATIENT_NAME + " = \"" + patient.getName() + "\" , "
                + PATIENT_DATEOFBIRTH + " = \"" + patient.getDateOfBirth() + "\" , "
                + PATIENT_NATIONALCODE + " = \"" + patient.getNationalCode() + "\" , "
                + PATIENT_PHONENUMBER + " = \"" + patient.getPhoneNumber() + "\" , "
                + PATIENT_ADDRESS + " = \"" + patient.getAddress() + "\" , "
                + PATIENT_PROFILEPICTUREPATH + " = \"" + patient.getProfilePicturePath() + "\" , "
                + PATIENT_RISKFACTOR + " = \"" + patient.getRiskFactor() + "\" , "
                + PATIENT_DRUGS + " = \"" + patient.getDrugs() + "\" , "
                + PATIENT_PASTMEDICALHISTORY + " = \"" + patient.getPastMedicalHistory() + "\" , "
                + PATIENT_PARACLINICANDPROCEDURE + " = \"" + patient.getParaClinicAndProcedure() + "\" , "
                + PATIENT_LAB + " = \"" + patient.getLab()
                + "\" WHERE " + PATIENT_ID + " = \"" + patient.getId() + "\";";
        db.execSQL(query);
    }


    public void deletePatient(Patient patient) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_PATIENT, PATIENT_ID + " = " + patient.getId(), null);
    }



    public ArrayList<Visit> getAllVisits() {
        SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_VISIT, null);
        ArrayList<Visit> visits = new ArrayList<Visit>();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(VISIT_ID));
                    int patientid = cursor.getInt(cursor.getColumnIndex(VISIT_PATIENTID));
                    String progressnote = cursor.getString(cursor.getColumnIndex(VISIT_PROGRESSNOTE));
                    String plan = cursor.getString(cursor.getColumnIndex(VISIT_PLAN));

                    Visit visit = new Visit();
                    visit.setId(id);
                    visit.setPatientId(patientid);
                    visit.setProgressNote(progressnote);
                    visit.setPlan(plan);

                    visits.add(visit);
                } while (cursor.moveToNext());
            }
        }

        return visits;
    }


    public ArrayList<Visit> getAllVisitsOfPatient(Patient patient) {
        SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_VISIT  +
                " WHERE " + VISIT_PATIENTID + " = " + patient.getId(), null);
        ArrayList<Visit> visits = new ArrayList<Visit>();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(VISIT_ID));
                    int patientid = cursor.getInt(cursor.getColumnIndex(VISIT_PATIENTID));
                    String progressnote = cursor.getString(cursor.getColumnIndex(VISIT_PROGRESSNOTE));
                    String plan = cursor.getString(cursor.getColumnIndex(VISIT_PLAN));

                    Visit visit = new Visit();
                    visit.setId(id);
                    visit.setPatientId(patientid);
                    visit.setProgressNote(progressnote);
                    visit.setPlan(plan);

                    visit.setExtraFields(getAllExtraFieldsOfVisit(visit));

                    visits.add(visit);
                } while (cursor.moveToNext());
            }
        }

        return visits;
    }


    public void insertVisit(Visit visit) {
        ContentValues values = new ContentValues();
        values.put(VISIT_PATIENTID, visit.getPatientId());
        values.put(VISIT_PROGRESSNOTE, visit.getProgressNote());
        values.put(VISIT_PLAN, visit.getPlan());
        this.getWritableDatabase().insert(TABLE_VISIT, null, values);
    }

    public void updateVisit(Visit visit) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_VISIT + " SET "
                + VISIT_PATIENTID + " = \"" + visit.getPatientId() + "\" , "
                + VISIT_PROGRESSNOTE + " = \"" + visit.getProgressNote() + "\" , "
                + VISIT_PLAN + " = \"" + visit.getPlan()
                + "\" WHERE " + VISIT_ID + " = \"" + visit.getId() + "\";";
        db.execSQL(query);
    }

    public void deleteVisit(Visit visit) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_VISIT, VISIT_ID + " = " + visit.getId(), null);
    }



    public ArrayList<Picture> getAllPictures() {
        SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PICTURE, null);
        ArrayList<Picture> pictures = new ArrayList<Picture>();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(PICTURE_ID));
                    int parentid = cursor.getInt(cursor.getColumnIndex(PICTURE_PARENTID));
                    String path = cursor.getString(cursor.getColumnIndex(PICTURE_PATH));

                    Picture picture = new Picture();
                    picture.setId(id);
                    picture.setParentId(parentid);
                    picture.setPath(path);

                    pictures.add(picture);
                } while (cursor.moveToNext());
            }
        }

        return pictures;
    }

    public void insertPicture(Picture picture) {
        ContentValues values = new ContentValues();
        values.put(PICTURE_PARENTID, picture.getParentId());
        values.put(PICTURE_PATH, picture.getPath());
        this.getWritableDatabase().insert(TABLE_PICTURE, null, values);
    }

    public void updatePicture(Picture picture) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_PICTURE + " SET "
                + PICTURE_PARENTID + " = \"" + picture.getParentId() + "\" , "
                + PICTURE_PATH + " = \"" + picture.getPath()
                + "\" WHERE " + PICTURE_ID + " = \"" + picture.getId() + "\";";
        db.execSQL(query);
    }

    public void deletePicture(Picture picture) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_PICTURE, PICTURE_ID + " = " + picture.getId(), null);
    }


    public ArrayList<ExtraField> getAllExtraFields() {
        SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EXTRAFIELD, null);
        ArrayList<ExtraField> extrafields = new ArrayList<ExtraField>();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(EXTRAFIELD_ID));
                    int type = cursor.getInt(cursor.getColumnIndex(EXTRAFIELD_TYPE));
                    int parentid = cursor.getInt(cursor.getColumnIndex(EXTRAFIELD_PARENTID));
                    String title = cursor.getString(cursor.getColumnIndex(EXTRAFIELD_TITLE));
                    String value = cursor.getString(cursor.getColumnIndex(EXTRAFIELD_VALUE));

                    ExtraField extrafield = new ExtraField();
                    extrafield.setId(id);
                    extrafield.setType(type);
                    extrafield.setParentId(parentid);
                    extrafield.setTitle(title);
                    extrafield.setValue(value);

                    extrafields.add(extrafield);
                } while (cursor.moveToNext());
            }
        }

        return extrafields;
    }


    public ArrayList<ExtraField> getAllExtraFieldsOfVisit(Visit visit) {
        SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EXTRAFIELD
                +" WHERE " + EXTRAFIELD_TYPE + " = " + ExtraField.VISIT_TYPE+
                " AND " + EXTRAFIELD_PARENTID + " = " + visit.getId(), null);
        ArrayList<ExtraField> extrafields = new ArrayList<ExtraField>();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(EXTRAFIELD_ID));
                    int type = cursor.getInt(cursor.getColumnIndex(EXTRAFIELD_TYPE));
                    int parentid = cursor.getInt(cursor.getColumnIndex(EXTRAFIELD_PARENTID));
                    String title = cursor.getString(cursor.getColumnIndex(EXTRAFIELD_TITLE));
                    String value = cursor.getString(cursor.getColumnIndex(EXTRAFIELD_VALUE));

                    ExtraField extrafield = new ExtraField();
                    extrafield.setId(id);
                    extrafield.setType(type);
                    extrafield.setParentId(parentid);
                    extrafield.setTitle(title);
                    extrafield.setValue(value);

                    extrafields.add(extrafield);
                } while (cursor.moveToNext());
            }
        }

        return extrafields;
    }


    public ArrayList<ExtraField> getAllExtraFieldsOfPatient(Patient patient) {
        SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EXTRAFIELD
                +" WHERE " + EXTRAFIELD_TYPE + " = " + ExtraField.PATIENT_TYPE+
                " AND " + EXTRAFIELD_PARENTID + " = " + patient.getId(), null);
        ArrayList<ExtraField> extrafields = new ArrayList<ExtraField>();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(EXTRAFIELD_ID));
                    int type = cursor.getInt(cursor.getColumnIndex(EXTRAFIELD_TYPE));
                    int parentid = cursor.getInt(cursor.getColumnIndex(EXTRAFIELD_PARENTID));
                    String title = cursor.getString(cursor.getColumnIndex(EXTRAFIELD_TITLE));
                    String value = cursor.getString(cursor.getColumnIndex(EXTRAFIELD_VALUE));

                    ExtraField extrafield = new ExtraField();
                    extrafield.setId(id);
                    extrafield.setType(type);
                    extrafield.setParentId(parentid);
                    extrafield.setTitle(title);
                    extrafield.setValue(value);

                    extrafields.add(extrafield);
                } while (cursor.moveToNext());
            }
        }

        return extrafields;
    }


    public void insertExtrafield(ExtraField extrafield) {
        ContentValues values = new ContentValues();
        values.put(EXTRAFIELD_TYPE, extrafield.getType());
        values.put(EXTRAFIELD_PARENTID, extrafield.getParentId());
        values.put(EXTRAFIELD_TITLE, extrafield.getTitle());
        values.put(EXTRAFIELD_VALUE, extrafield.getValue());
        this.getWritableDatabase().insert(TABLE_EXTRAFIELD, null, values);
    }


    public void updateExtrafield(ExtraField extrafield) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_EXTRAFIELD + " SET "
                + EXTRAFIELD_TYPE + " = \"" + extrafield.getType() + "\" , "
                + EXTRAFIELD_PARENTID + " = \"" + extrafield.getParentId() + "\" , "
                + EXTRAFIELD_TITLE + " = \"" + extrafield.getTitle() + "\" , "
                + EXTRAFIELD_VALUE + " = \"" + extrafield.getValue()
                + "\" WHERE " + EXTRAFIELD_ID + " = \"" + extrafield.getId() + "\";";
        db.execSQL(query);
    }

    public void deleteExtrafield(ExtraField extrafield) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_EXTRAFIELD, EXTRAFIELD_ID + " = " + extrafield.getId(), null);
    }
}

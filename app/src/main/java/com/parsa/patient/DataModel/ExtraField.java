package com.parsa.patient.DataModel;

/**
 * Created by parsa on 2015-01-04.
 */
public class ExtraField {
    int id;
    int type;    // 0 for patient      1 for visit
    int parentId;
    String title;
    String value;
}

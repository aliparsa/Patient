package com.parsa.patient.DataModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.parsa.patient.R;

import java.util.ArrayList;

/**
 * Created by parsa on 2015-01-04.
 */
public class Patient {
    int id;
    String name;
    String dateOfBirth;
    String nationalCode;
    String PhoneNumber;
    String Address;
    String profilePicturePath;

    String riskFactor;
    String drugs;
    String pastMedicalHistory;
    String paraClinicAndProcedure;
    String Lab;
    private ArrayList<ExtraField> extraFields;


    public View getView(Context context, View oldView) {
        if (oldView == null || !(oldView.getTag() instanceof Patient)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            oldView = inflater.inflate(R.layout.item_patient, null);
            Holder holder = new Holder();
            oldView.setTag(holder);
            getItem(holder, oldView);
            return oldView;
        } else {          Holder holder = (Holder) oldView.getTag();
            getItem(holder, oldView);
            return oldView;      }
    }

    private void getItem(Holder holder, View view) {
        holder.patient = this;  if (holder.id == null)
            holder.id = (TextView) view.findViewById(R.id.id);

        if (holder.name == null)
            holder.name = (TextView) view.findViewById(R.id.name);

        if (holder.dateofbirth == null)
            holder.dateofbirth = (TextView) view.findViewById(R.id.dateofbirth);

        if (holder.nationalcode == null)
            holder.nationalcode = (TextView) view.findViewById(R.id.nationalcode);

        if (holder.phonenumber == null)
            holder.phonenumber = (TextView) view.findViewById(R.id.phonenumber);

        if (holder.address == null)
            holder.address = (TextView) view.findViewById(R.id.address);

        if (holder.profilepicturepath == null)
            holder.profilepicturepath = (TextView) view.findViewById(R.id.profilepicturepath);

        if (holder.riskfactor == null)
            holder.riskfactor = (TextView) view.findViewById(R.id.riskfactor);

        if (holder.drugs == null)
            holder.drugs = (TextView) view.findViewById(R.id.drugs);

        if (holder.pastmedicalhistory == null)
            holder.pastmedicalhistory = (TextView) view.findViewById(R.id.pastmedicalhistory);

        if (holder.paraclinicandprocedure == null)
            holder.paraclinicandprocedure = (TextView) view.findViewById(R.id.paraclinicandprocedure);

        if (holder.lab == null)
            holder.lab = (TextView) view.findViewById(R.id.lab);

        holder.id.setText(this.getId() + "");
        holder.name.setText(this.getName());
        holder.dateofbirth.setText(this.getDateOfBirth());
        holder.nationalcode.setText(this.getNationalCode());
        holder.phonenumber.setText(this.getPhoneNumber());
        holder.address.setText(this.getAddress());
        holder.profilepicturepath.setText(this.getProfilePicturePath());
        holder.riskfactor.setText(this.getRiskFactor());
        holder.drugs.setText(this.getDrugs());
        holder.pastmedicalhistory.setText(this.getPastMedicalHistory());
        holder.paraclinicandprocedure.setText(this.getParaClinicAndProcedure());
        holder.lab.setText(this.getLab());
    }

    public void setExtraFields(ArrayList<ExtraField> extraFields) {
        this.extraFields = extraFields;
    }

    public ArrayList<ExtraField> getExtraFields() {
        return extraFields;
    }

    public class Holder {
        TextView id;
        TextView name;
        TextView dateofbirth;
        TextView nationalcode;
        TextView phonenumber;
        TextView address;
        TextView profilepicturepath;
        TextView riskfactor;
        TextView drugs;
        TextView pastmedicalhistory;
        TextView paraclinicandprocedure;
        TextView lab;

        Patient patient;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public String getRiskFactor() {
        return riskFactor;
    }

    public void setRiskFactor(String riskFactor) {
        this.riskFactor = riskFactor;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }

    public String getPastMedicalHistory() {
        return pastMedicalHistory;
    }

    public void setPastMedicalHistory(String pastMedicalHistory) {
        this.pastMedicalHistory = pastMedicalHistory;
    }

    public String getParaClinicAndProcedure() {
        return paraClinicAndProcedure;
    }

    public void setParaClinicAndProcedure(String paraClinicAndProcedure) {
        this.paraClinicAndProcedure = paraClinicAndProcedure;
    }

    public String getLab() {
        return Lab;
    }

    public void setLab(String lab) {
        Lab = lab;
    }
}

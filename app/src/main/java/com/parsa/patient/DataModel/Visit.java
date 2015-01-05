package com.parsa.patient.DataModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.parsa.patient.Adapter.ListViewObjectAdapter;
import com.parsa.patient.R;

import java.util.ArrayList;

/**
 * Created by parsa on 2015-01-04.
 */
public class Visit implements ListViewObjectAdapter.IListViewItem {
    int id;
    int patientId;
    String progressNote;
    String plan;
    String creationDate;

    ArrayList<ExtraField> extraFields;

    public Visit(int id, int patientId, String progressNote, String plan,String creationDate, ArrayList<ExtraField> extraFields) {
        this.id = id;
        this.patientId = patientId;
        this.progressNote = progressNote;
        this.plan = plan;
        this.creationDate = creationDate;
        this.extraFields = extraFields;
    }

    public Visit( int patientId, String progressNote, String plan,String creationDate, ArrayList<ExtraField> extraFields) {
        this.patientId = patientId;
        this.progressNote = progressNote;
        this.plan = plan;
        this.creationDate = creationDate;
        this.extraFields = extraFields;
    }

    public Visit() {

    }


    public View getView(Context context, View oldView) {
        if (oldView == null || !(oldView.getTag() instanceof Visit)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            oldView = inflater.inflate(R.layout.item_visit, null);
            Holder holder = new Holder();
            oldView.setTag(holder);
            getItem(holder, oldView);
            return oldView;
        } else {          Holder holder = (Holder) oldView.getTag();
            getItem(holder, oldView);
            return oldView;      }
    }

    private void getItem(Holder holder, View view) {
        holder.visit = this;  if (holder.id == null)
            holder.id = (TextView) view.findViewById(R.id.id);

        if (holder.patientid == null)
            holder.patientid = (TextView) view.findViewById(R.id.patientid);

        if (holder.progressnote == null)
            holder.progressnote = (TextView) view.findViewById(R.id.progressnote);

        if (holder.plan == null)
            holder.plan = (TextView) view.findViewById(R.id.plan);

        holder.id.setText(this.getId()+"");
        holder.patientid.setText(this.getPatientId()+"");
        holder.progressnote.setText(this.getProgressNote());
        holder.plan.setText(this.getPlan());
    }

    public class Holder {
        TextView id;
        TextView patientid;
        TextView progressnote;
        TextView plan;

        Visit visit;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getProgressNote() {
        return progressNote;
    }

    public void setProgressNote(String progressNote) {
        this.progressNote = progressNote;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public ArrayList<ExtraField> getExtraFields() {
        return extraFields;
    }

    public void setExtraFields(ArrayList<ExtraField> extraFields) {
        this.extraFields = extraFields;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}

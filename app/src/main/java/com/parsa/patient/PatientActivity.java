package com.parsa.patient;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parsa.patient.Adapter.ListViewObjectAdapter;
import com.parsa.patient.DataModel.Patient;
import com.parsa.patient.DataModel.Visit;
import com.parsa.patient.Helper.DatabaseHelper;
import com.parsa.patient.R;

import java.util.ArrayList;

public class PatientActivity extends Activity {

    private Context context;
    Patient patient;
    ListView visitsListView;
    TextView patientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        context=this;
        visitsListView = (ListView) findViewById(R.id.visittListView);
        patientName = (TextView) findViewById(R.id.patientNameTextView);




        patient = (Patient) getIntent().getSerializableExtra("patient");


        // fill patient info
        patientName.setText(patient.getName()+" \n "+patient.getNationalCode()+" \n "+patient.getAddress());


        // add some fake visits
        new DatabaseHelper(context).emptyVisitTable();
        new DatabaseHelper(context).insertVisit(new Visit(patient.getId(),"pg note","plane",null));
        new DatabaseHelper(context).insertVisit(new Visit(patient.getId(),"pg note","plane",null));
        new DatabaseHelper(context).insertVisit(new Visit(patient.getId(),"pg note","plane",null));
        new DatabaseHelper(context).insertVisit(new Visit(patient.getId(),"pg note","plane",null));
        new DatabaseHelper(context).insertVisit(new Visit(patient.getId(),"pg note","plane",null));

        loadVisits(patient);
    }

    private void loadVisits(Patient patient) {
        ArrayList<Visit> visits = new DatabaseHelper(context).getAllVisitsOfPatient(patient);
        visitsListView.setAdapter(new ListViewObjectAdapter(context,visits));
        visitsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(context,i+"",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.patient, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

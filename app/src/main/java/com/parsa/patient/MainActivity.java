package com.parsa.patient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parsa.patient.Adapter.ListViewObjectAdapter;
import com.parsa.patient.DataModel.Patient;
import com.parsa.patient.Helper.DatabaseHelper;

import java.util.ArrayList;


public class MainActivity extends Activity {

    // Define Fields
    ListView patientListView;
    private android.content.Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        patientListView = (ListView) findViewById(R.id.patientListView);

        // insert some fake patient;
        DatabaseHelper db = new DatabaseHelper(context);
        db.emptyPatientTable();
        db.insertPatient(new Patient("ali","2014/10/25","2471754185","09364529674","shiraz - bolvar nasr","","risk factor","drugs","past medical history","para clinic and procedure","lab"));
        db.insertPatient(new Patient("reza","2014/10/25","2471754185","09364529674","shiraz - bolvar nasr","","risk factor","drugs","past medical history","para clinic and procedure","lab"));
        db.insertPatient(new Patient("akbar","2014/10/25","2471754185","09364529674","shiraz - bolvar nasr","","risk factor","drugs","past medical history","para clinic and procedure","lab"));
        db.insertPatient(new Patient("kazem","2014/10/25","2471754185","09364529674","shiraz - bolvar nasr","","risk factor","drugs","past medical history","para clinic and procedure","lab"));


        loadPatients();

    }

    private void loadPatients() {
        final ArrayList<Patient> patients =  new DatabaseHelper(context).getAllPatients();
        patientListView.setAdapter( new ListViewObjectAdapter(context,patients));
        patientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Patient patient = ((Patient.Holder) view.getTag()).getPatient();

                // call patient activity
                Intent intent = new Intent(context,PatientActivity.class);
                intent.putExtra("patient",patient);
                startActivity(intent);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

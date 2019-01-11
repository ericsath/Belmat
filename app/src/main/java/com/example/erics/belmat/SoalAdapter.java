package com.example.erics.belmat;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.erics.belmat.model.Soal;

public class SoalAdapter extends ArrayAdapter<Soal> {
    Context context;
    int layoutResourceId;
    ArrayList<Soal> data = new ArrayList<Soal>();

    public SoalAdapter(Context context, int layoutResourceId,
                             ArrayList<Soal> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ImageHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ImageHolder();
            holder.txtName = (TextView) row.findViewById(R.id.id);
            holder.txtClinicalSpecialty = (TextView) row.findViewById(R.id.kategori);
            holder.txtNameOfClinicHospital = (TextView) row.findViewById(R.id.soal);
            holder.txtCity = (TextView) row.findViewById(R.id.jawab);
            row.setTag(holder);
        } else {
            holder = (ImageHolder) row.getTag();
        }
        Soal init = data.get(position);
        holder.txtName.setText(String.valueOf(data.get(position).getIdSoal()));
        holder.txtClinicalSpecialty.setText(init.getKategori());
        holder.txtNameOfClinicHospital.setText(init.getSoal());
        holder.txtCity.setText(init.getJawab());
        return row;
    }

    static class ImageHolder {
        TextView txtName;
        TextView txtClinicalSpecialty;
        TextView txtNameOfClinicHospital;
        TextView txtCity;
    }
}

package com.example.yodono2;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class InfoAdapter extends BaseExpandableListAdapter {

    Context context;

    String[] infotitles ={
            "CONDICIONES PARA DONAR",
            "¿A QUIÉN AYUDO?",
            "USOS DE LA SANGRE",
            "¿Cuánto tiempo me lleva donar sangre?",
            "¿En cuanto tiempo recupero lo donado?",
            "¿Antes de proceder a la donacióntengo algún paso previo?",
            "¿Cuánto ayuno es necesario?",
            "Me vacuné, ¿Puedo donar?",
            "¿Si fumo, puedo donar?",
            "¿Me puedo contagiar con algo por donar sangre?",
            "¿Puedo donar si estoy tomando anticonceptivos?",
            "¿Quien conocerá los resultados de mis análisis?",
            "¿Porqué me hacen preguntas antes de la donación si igual se analizan todas las unidades de sangre?",
            "¿Si se me detecta alguna alteración en los análisis que se realizan después de la donación me informan?"

    };

    String[][] infodescriptions ={
            {"•Tener entre 18 y 65 años.\n•Pesar más de 50 kg. \n•Tener la Cédula de Identidad vigente y en buen estado.\n" +
                    "•Haber descansado 6 horas la noche anterior.\n•Tener buen estado de salud.\n•Las 4 horas antes de donar no ingerir sólidos ni lácteos, pero sí recomendamos que tomes muchos líquidos"},
            {"Algún día alguien de tu familia puede necesitar sangre, o también puede suceder que sin preverlo, tú mismo puedas requerir una transfusión.\n" +
                    "\n" +
                    "La donación de sangre hace posible los procedimientos en siniestros de tránsito, intervenciones quirúrgicas, trasplantes de órganos, hemorragias, tratamiento de pacientes con cáncer y muchas otras patologías.\n" +
                    "\n" +
                    "Cientos de uruguayos reciben cada día una transfusión sanguínea. La mayoría de éstas, de donantes anónimos que con su actitud están permitiendo salvar otras vidas."},
            {"Tu donación ayuda a pacientes en tratamiento de:\n" +
                    "   •Anemia\n   •Diálisis\n   •Cirugía\n   •Leucemia\n   •Transplante de órganos\n   •Traumatismos graves por accidentes" +
                    "\n   •Quemaduras\n   •Problemas de coagulación\n   •Cáncer"},
            {"En total no más de 30 minutos.\n" +
                    "La extracción lleva entre 7 y 10 minutos."},
            {"Cada 120 días toda tu sangre se renueva, da igual que hayas donado o no."},
            {"Sí, antes tienes que concretar los siguientes pasos: completar una encuesta, tener una entrevista médica, " +
                    "pasar por un control de muestra de sangre, la tensión arterial y el pulso. Si todos los parámetros están bien, " +
                    "pasas a realizar la donación."},
            {"No es necesario un ayuno total, las 4 horas previas,  debes beber abundantes líquidos que no sean lácteos. Puedes beber té, café, mate, jugos, refrescos. También puedes comer frutas."},
            {"Depende de cuál vacuna te hayas dado, es el tiempo que debes esperar, generalmente unos días. Te recomendamos siempre consultar al Servicio Nacional de Sangre."},
            {"Sí. No obstante, no es conveniente que fumes antes de la donación o inmediatamente después de haber donado."},
            {"No existe ninguna posibilidad de contagio. En el Servicio Nacional de Sangre estamos tomando todas las precauciones para que las personas concurran y no se expongan a ningún riesgo."},
            {"Sí, no existe ninguna contraindicación al respecto."},
            {"El médico , los datos son absolutamente confidenciales" +
                    "y se someten al mismo secreto que cualquier otra" +
                    "consulta médica"},
            {"Si bien los análisis son muy fiables, hay un momento denominado período ventana en el que una determinada enfermedad no se ha manifestado." +
                    " En este lapso, la persona está contagiada pero todavía no han aparecido los anticuerpos para combatir al agente infeccioso. " +
                    "Esta fase no se puede detectar en el laboratorio y por eso es importante que seas claro y sincero al declarar si has participado o no" +
                    " en prácticas consideradas de riesgo para la donación."},
            {"Sí, luego de cualquier examen con resultados anómalos, se te informa por medio de una entrevista médica en la que se te orienta respecto a los pasos a seguir."}
    };

    public InfoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return infotitles.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return infodescriptions[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return infotitles[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return infodescriptions[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String infoTitle = (String)getGroup(groupPosition);
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.info_titles,null);

        }
        TextView infoTitle2=convertView.findViewById(R.id.infoTitleView);
        infoTitle2.setTypeface(null, Typeface.BOLD);
        infoTitle2.setText(infoTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String infoDescription = (String)getChild(groupPosition,childPosition);
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.info_description,null);
        }
        TextView infoDescription2=convertView.findViewById(R.id.infoTitleDescription);
        infoDescription2.setText(infoDescription);
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}


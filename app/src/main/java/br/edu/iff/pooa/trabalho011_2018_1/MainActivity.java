package br.edu.iff.pooa.trabalho011_2018_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.areaTotal = (EditText) findViewById(R.id.editAreaValor);
        this.mViewHolder.btnCalcular = (Button) findViewById(R.id.btnCalcular);
        this.mViewHolder.textQtdLatas = (TextView) findViewById(R.id.textQtdLatas);
        this.mViewHolder.textQtdGaloes = (TextView) findViewById(R.id.textQtdGaloes);
        this.mViewHolder.textValor = (TextView) findViewById(R.id.textValor);

        this.mViewHolder.btnCalcular.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id == this.mViewHolder.btnCalcular.getId()){
            int qtdLata, qtdGalao;
            double valorLata = 80.00, valorGalao = 25.00;
            double aux, areaDePintura = Double.valueOf(this.mViewHolder.areaTotal.getText().toString()) / 6;

            aux = (areaDePintura / 3.6);
            qtdLata = (int) aux / 5;
            aux = (aux % 5);
            qtdGalao = (int) aux;
            if ( aux == 0 )
                qtdGalao = 0;
            else
                qtdGalao = (int) aux+1;

            this.mViewHolder.textQtdLatas.setText(String.format("Quantidade de Latas: %d", qtdLata));
            this.mViewHolder.textQtdGaloes.setText(String.format("Quantidade de Galões: %d",qtdGalao));
            this.mViewHolder.textValor.setText(String.format("Total: R$ %.2f", (qtdGalao*valorGalao)+(qtdLata*valorLata)));
        }

    }

    private static class ViewHolder{
        EditText areaTotal;
        Button btnCalcular;
        TextView textQtdLatas, textQtdGaloes, textValor;
    }
}

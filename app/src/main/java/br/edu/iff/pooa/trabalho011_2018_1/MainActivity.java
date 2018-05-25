package br.edu.iff.pooa.trabalho011_2018_1;

import android.annotation.SuppressLint;
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
        this.mViewHolder.textApenasLatas = (TextView) findViewById(R.id.textApenasLatas);
        this.mViewHolder.textApenasGaloes = (TextView) findViewById(R.id.textApenasGaloes);
        this.mViewHolder.textViewMelhorC = (TextView) findViewById(R.id.textViewMelhorC);

        this.mViewHolder.btnCalcular.setOnClickListener(this);

        this.clearTexts();
    }

    @SuppressLint("WrongConstant")
    private void clearTexts() {
        this.mViewHolder.textQtdLatas.setText("");
        this.mViewHolder.textQtdGaloes.setText("");
        this.mViewHolder.textValor.setText("");
        this.mViewHolder.textApenasLatas.setText("");
        this.mViewHolder.textApenasGaloes.setText("");
        this.mViewHolder.textViewMelhorC.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id == this.mViewHolder.btnCalcular.getId()) {
            int qtdLata, qtdGalao;
            double valorLata = 80.00, valorGalao = 25.00;
            double aux, coberturaLata=18, coberturaGalao=3.6, areaDePintura = Double.valueOf(this.mViewHolder.areaTotal.getText().toString()) / 6;

            apenasLatas(areaDePintura,valorLata);
            apenasGaloes(areaDePintura,valorGalao);

            qtdLata = (int) (areaDePintura / 18);
            aux = areaDePintura % 18;

            if(aux == 0)
                qtdGalao = 0;
            else {
                qtdGalao = (int) (aux / 3.6);

                if(qtdGalao == 0 || qtdGalao * 3.6 < areaDePintura)
                    qtdGalao++;

                if(qtdGalao * valorGalao > valorLata) {
                    qtdGalao = 0;
                    qtdLata++;
                }
            }

            this.mViewHolder.textViewMelhorC.setVisibility(View.VISIBLE);
            this.mViewHolder.textQtdLatas.setText(String.format("Quantidade de Latas: %d", qtdLata));
            this.mViewHolder.textQtdGaloes.setText(String.format("Quantidade de Galões: %d",qtdGalao));
            this.mViewHolder.textValor.setText(String.format("Total: R$ %.2f", (qtdGalao*valorGalao)+(qtdLata*valorLata)));
        }

    }

    private void apenasGaloes(double areaDePintura, double valorGalao) {
        int qtd;
        double resto = 0.0;
        float valor;

        qtd = (int) (areaDePintura / 3.6);
        resto = areaDePintura % 3.6;
        if (resto > 0)
            qtd++;

        valor = (float) (qtd * valorGalao);

        this.mViewHolder.textApenasGaloes.setText(String.format("Quantidade Apenas Galões: %d - Custo: R$ %.2f", qtd, valor));
    }

    private void apenasLatas(double areaDePintura, double valorLata) {
        int qtd;
        double resto = 0.0;
        float valor;

        qtd = (int) areaDePintura / 18;
        resto = areaDePintura % 18;
        if (resto > 0)
            qtd++;

        valor = (float) (qtd * valorLata);
        this.mViewHolder.textApenasLatas.setText(String.format("Quantidade Apenas Latas: %d - Custo: R$ %.2f", qtd, valor));
    }

    private static class ViewHolder{
        EditText areaTotal;
        Button btnCalcular;
        TextView textQtdLatas, textQtdGaloes, textValor, textApenasLatas, textApenasGaloes, textViewMelhorC;
    }
}

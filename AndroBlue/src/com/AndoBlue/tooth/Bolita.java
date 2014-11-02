package com.AndoBlue.tooth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Bolita extends View  {

	float i1,i2, brujula;
	Paint paint;
	public Bitmap b;	
	
	int imagen=0;
	public Bolita(Context context) {
		super(context);
		
	}
		
	public Bolita(Context context, AttributeSet attrs){
		super(context, attrs);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		
	}
		
	public Bolita(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
		
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
	}
	
	public void actualizar(float valor, float valor2, float brujula) {
		i1 = valor;
		i2 = valor2;
		this.brujula= brujula;
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int w = this.getMeasuredWidth();
		int h = this.getMeasuredHeight();
		
		float x = w/2 + (i1 / 90)*w/2;
		float y = h/2 + (i2 / 90)*h/3;
				
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.BLUE);
		
		
		Paint p;
        p=new Paint();
        switch (imagen){
        
        //primera linea
        case 21:
        	b=BitmapFactory.decodeResource(getResources(), R.drawable.arriba2);
        	break;
        case 22:
        	b=BitmapFactory.decodeResource(getResources(), R.drawable.arriba2);
        	break;
        case 23:
        	b=BitmapFactory.decodeResource(getResources(), R.drawable.izquierda);
        	break;
        case 24:
        	b=BitmapFactory.decodeResource(getResources(), R.drawable.abajo);
        	break;
        case 25:
        	b=BitmapFactory.decodeResource(getResources(), R.drawable.abajo);
        	break;
       // linea de enmedio
        case 31:
        	b=BitmapFactory.decodeResource(getResources(), R.drawable.arriba2);
        	break;
        case 32:
        	b=BitmapFactory.decodeResource(getResources(), R.drawable.arriba2);
        	break;
        case 33:
        	b=BitmapFactory.decodeResource(getResources(), R.drawable.arriba);
        	break;
        case 34:
        	b=BitmapFactory.decodeResource(getResources(), R.drawable.abajo);
        	break;
        case 35:
        	b=BitmapFactory.decodeResource(getResources(), R.drawable.abajo);
        	break;
        	// linea de la derecha
        case 41:
        	b=BitmapFactory.decodeResource(getResources(), R.drawable.arriba);
        	break;
        case 42:
        	b=BitmapFactory.decodeResource(getResources(), R.drawable.arriba);
        	break;
        case 43:
        	b=BitmapFactory.decodeResource(getResources(), R.drawable.derecha);
        	break;
        case 44:
        	b=BitmapFactory.decodeResource(getResources(), R.drawable.abajo2);
        	break;
        case 45:
        	b=BitmapFactory.decodeResource(getResources(), R.drawable.abajo2);
        	break;
        	
        	
        default:
        	b=BitmapFactory.decodeResource(getResources(), R.drawable.izquierda);

        }
			
        p.setColor(Color.RED);
        canvas.drawBitmap(b, x, y, p);

		
		
	//	canvas.drawCircle(x, y, w/20, paint);
		
		paint.setColor(Color.YELLOW);
		paint.setTextSize(20);
		//canvas.drawText("X="+Integer.toString((int)i1), x-25, y-5, paint);
		//canvas.drawText("Y="+Integer.toString((int)i2), x-25, y+15, paint);
	}
	public int brujulla( ){
		int valor=1;
		if(brujula>-30 && brujula<30){
			valor=0;
		}
		if(brujula>65 && brujula<115){
			valor=90;
		}
		if(brujula>-125 && brujula<-65){
			valor=-90;
		}
		if(brujula>135 || brujula==180 || brujula <-135 ){
			valor=180;
		}
		
		return valor;
	}
	
	public String setImagen(int num){
		imagen=num;
		
			return "valor: "+imagen;
	}
	
	
}

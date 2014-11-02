package com.AndoBlue.tooth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Grid extends View {
	
	Paint paint;
	int count=0;
	int count2=0;
	public Grid(Context context) {
		super(context);
	}
		
	public Grid(Context context, AttributeSet attrs){
		super(context, attrs);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}
		
	public Grid(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
		
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		int w = getMeasuredWidth();
		int h = getMeasuredHeight();

		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		paint.setColor(Color.RED);
		
		for(int x= 0; x <= w; x+= w/5){
			if(count<=4 && count!=0){
			canvas.drawLine(x, h/2-h/3, x, h/2+h/3, paint);
				}
		count++;
		}
	
		for(int y= h/6; y <= 5*h/6; y+=2*h/15)
		
			canvas.drawLine(w/5, y, (w/5)*4, y, paint);
	
		
	}
}
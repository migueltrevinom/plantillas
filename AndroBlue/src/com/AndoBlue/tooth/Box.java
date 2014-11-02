package com.AndoBlue.tooth;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Box extends View {

	int x = 3;
	int y = 3;
	Paint paint;
	
	
	public Box(Context context) {
		super(context);
	}
		
	public Box(Context context, AttributeSet attrs){
		super(context, attrs);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}
		
	public Box(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
		
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
	}
	
	public void updbox(int xc, int xy) {
		x = xc;
		y = xy;
		this.invalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		int w = this.getMeasuredWidth();
		int h = this.getMeasuredHeight();

		int posx = w/2 + (x-3)*w/5;
		int posy = h/2 + (y-3)*2*h/15;
		
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		paint.setColor(Color.CYAN);
		if(x==2 || x==3 || x==4)
		{
			
		canvas.drawRect(posx-w/10+5, posy-h/15+3, posx+w/10-5, posy+h/15-3, paint);}
		
	}
}
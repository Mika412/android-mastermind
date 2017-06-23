/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.mykha.mastermindandroid;

import android.content.Context;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mykha.mastermindandroid.Strategy.Answer;

import java.util.ArrayList;
import java.util.List;

public class GuessAdapter extends RecyclerView.Adapter<GuessAdapter.GuessAdapterViewHolder> {

    private static final int VIEW_TYPE_MAIN = 0;
    private static final int VIEW_TYPE_READ = 1;

    // Flag to determine if we want to use a separate view for "today".
    private boolean mUseFocused = true;
    private Context mContext;

    private List<ListItem> itemsList;

    public class GuessAdapterViewHolder extends RecyclerView.ViewHolder {
        public View guessCircle1;
        public View guessCircle2;
        public View guessCircle3;
        public View guessCircle4;

        public TextView mWonText;
        public GridLayout mGridLayout;

        public List<View> miniCircles = new ArrayList<>();

        public GuessAdapterViewHolder(View view, Context context) {
            super(view);
            mContext = context;
            mWonText = (TextView) view.findViewById(R.id.won);
            mGridLayout = (GridLayout) view.findViewById(R.id.gridLayout);
            guessCircle1 = (View) view.findViewById(R.id.cicleOne);
            guessCircle2 = (View) view.findViewById(R.id.cicleTwo);
            guessCircle3 = (View) view.findViewById(R.id.cicleThree);
            guessCircle4 = (View) view.findViewById(R.id.cicleFour);

            miniCircles.add((View) view.findViewById(R.id.miniCircleOne));
            miniCircles.add((View) view.findViewById(R.id.miniCircleTwo));
            miniCircles.add((View) view.findViewById(R.id.miniCircleThree));
            miniCircles.add((View) view.findViewById(R.id.miniCircleFour));
        }

    }


    public GuessAdapter(Context mContext, List<ListItem> itemsList) {
        this.mContext = mContext;
        this.itemsList = itemsList;
    }

    @Override
    public GuessAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        return new GuessAdapterViewHolder(itemView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(GuessAdapterViewHolder holder, int position) {
        ListItem item = itemsList.get(position);
        Log.e("TAG", item.getCode().toString().charAt(0)+"");
        Log.e("TAG", " " +GuessingComputer.colors.size());

        holder.guessCircle1.setBackgroundTintList(mContext.getResources().getColorStateList(GuessingComputer.colors.get(Character.getNumericValue(item.getCode().toString().charAt(0)) - 1)));
        holder.guessCircle2.setBackgroundTintList(mContext.getResources().getColorStateList(GuessingComputer.colors.get(Character.getNumericValue(item.getCode().toString().charAt(1)) - 1)));
        holder.guessCircle3.setBackgroundTintList(mContext.getResources().getColorStateList(GuessingComputer.colors.get(Character.getNumericValue(item.getCode().toString().charAt(2)) - 1)));
        holder.guessCircle4.setBackgroundTintList(mContext.getResources().getColorStateList(GuessingComputer.colors.get(Character.getNumericValue(item.getCode().toString().charAt(3)) - 1)));

        if(item.getWon()){
            holder.mWonText.setVisibility(View.VISIBLE);
            holder.mGridLayout.setVisibility(View.INVISIBLE);
        }else{
            holder.mWonText.setVisibility(View.INVISIBLE);
            holder.mGridLayout.setVisibility(View.VISIBLE);

            Answer gAnswer = item.getAnswer();
            int black = gAnswer.blacks;
            int whites = gAnswer.whites;
            for(int i = 0; i < 4; i++){
                if(black > 0){
                    holder.miniCircles.get(i).setVisibility(View.VISIBLE);
                    holder.miniCircles.get(i).setBackgroundTintList(mContext.getResources().getColorStateList(R.color.colorBlacks));
                    black--;
                }else if(whites > 0){
                    holder.miniCircles.get(i).setVisibility(View.VISIBLE);
                    holder.miniCircles.get(i).setBackgroundTintList(mContext.getResources().getColorStateList(R.color.colorWhites));
                    whites--;
                }else{
                    holder.miniCircles.get(i).setVisibility(View.INVISIBLE);
                }
            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        return (position == 0 && mUseFocused) ? VIEW_TYPE_MAIN : VIEW_TYPE_READ;
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
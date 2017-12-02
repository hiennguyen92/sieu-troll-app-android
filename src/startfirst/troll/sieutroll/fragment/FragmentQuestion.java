package startfirst.troll.sieutroll.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import startfirst.troll.sieutroll.GameOverActivity;
import startfirst.troll.sieutroll.QuestionActivity;
import startfirst.troll.sieutroll.R;
import startfirst.troll.sieutroll.WinActivity;
import startfirst.troll.sieutroll.dto.CauHoi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceActivity.Header;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentQuestion extends Fragment {

	TextView tvCauSo, tvSoMang, tvNoiDung;
	Button btnGoiDien, btnHoiYKien, btnNamMuoiNamMuoi, btnDoiCauHoi;
	Button btnA, btnB, btnC, btnD;
	int _position, _heart;
	ArrayList<CauHoi> _data;
	Boolean isGoiDien,isYKien,is50,isDoiCauHoi;
	
	final MediaPlayer mp = new MediaPlayer();

	public static FragmentQuestion newFragment(ArrayList<CauHoi> data, int pos,
			int heart,Boolean goidien,Boolean ykien, Boolean i50,Boolean DoiCauHoi) {
		FragmentQuestion frag = new FragmentQuestion();
		frag._data = data;
		frag._position = pos;
		frag._heart = heart;
		frag.isGoiDien = goidien;
		frag.isYKien = ykien;
		frag.is50 = i50;
		frag.isDoiCauHoi = DoiCauHoi;
		return frag;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment, container, false);
		tvCauSo = (TextView) view.findViewById(R.id.txtCauSo);
		tvSoMang = (TextView) view.findViewById(R.id.txtSoMang);
		tvNoiDung = (TextView) view.findViewById(R.id.txtNoiDung);
		btnGoiDien = (Button) view.findViewById(R.id.btnGoiDien);
		btnHoiYKien = (Button) view.findViewById(R.id.btnHoiYKien);
		btnNamMuoiNamMuoi = (Button) view.findViewById(R.id.btnNamMuoiNamMuoi);
		btnDoiCauHoi = (Button) view.findViewById(R.id.btnDoiCauHoi);
		btnA = (Button) view.findViewById(R.id.btnA);
		btnB = (Button) view.findViewById(R.id.btnB);
		btnC = (Button) view.findViewById(R.id.btnC);
		btnD = (Button) view.findViewById(R.id.btnD);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		btnNamMuoiNamMuoi.setEnabled(is50);
		btnDoiCauHoi.setEnabled(isDoiCauHoi);
		btnGoiDien.setEnabled(isGoiDien);
		btnHoiYKien.setEnabled(isYKien);
		
		tvCauSo.setText("Câu Số " + _data.get(_position).get_id());
		tvNoiDung.setText(_data.get(_position).get_cauhoi());
		tvSoMang.setText("Fap:" + _heart);
		btnA.setText("[A] "+_data.get(_position).get_cau_a());
		btnB.setText("[B] "+_data.get(_position).get_cau_b());
		btnC.setText("[C] "+_data.get(_position).get_cau_c());
		btnD.setText("[D] "+_data.get(_position).get_cau_d());

		btnA.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (_data.get(_position).get_dapan().equals("a")) {	
					if (_position == 99) {
						Intent iWin = new Intent(getActivity(), WinActivity.class);
						startActivity(iWin);
						getActivity().finish();
					}else {
				        FragmentTransaction mFragTransaction = getActivity().getSupportFragmentManager().beginTransaction();
						mFragTransaction.replace(R.id.content_frame,FragmentQuestion.newFragment(_data, _position + 1,_heart,isGoiDien,isYKien,is50,isDoiCauHoi)).commit();
						
						View view = getActivity().getLayoutInflater().inflate(R.layout.giaithich_layout, null);
						TextView tv = (TextView)view.findViewById(R.id.txtGiaiThich);
						String txt = _data.get(_position).get_giaithich();
						Toast toast = new Toast(getActivity());
						if (txt == null) {
						}else {
							tv.setText(txt);
							toast.setDuration(Toast.LENGTH_LONG);
							toast.setView(view);
							toast.show();
						}
					}

				}else {
					if(mp.isPlaying())
			        {  
			            mp.stop();
			            mp.reset();
			        } 
			        try {

			            AssetFileDescriptor afd;
			            afd = getActivity().getAssets().openFd("fai.mp3");
			            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
			            mp.prepare();
			            mp.start();
			        } catch (IllegalStateException e) {
			            e.printStackTrace();
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
					_heart--;
					tvSoMang.setText("Fap: "+_heart);
				}
				if (_heart==0) {
					Intent iOver = new Intent(getActivity(), GameOverActivity.class);
					startActivity(iOver);
					getActivity().finish();
				}
				
				
				if (_position == 29) {
					View view = getActivity().getLayoutInflater().inflate(R.layout.bua_nhan_layout, null);
					Toast toast = new Toast(getActivity());
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(view);
					toast.show();
				}
				if (_position == 49) {
					View view = getActivity().getLayoutInflater().inflate(R.layout.troll_nhan_layout, null);
					Toast toast = new Toast(getActivity());
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(view);
					toast.show();
				}
				if (_position == 74) {
					View view = getActivity().getLayoutInflater().inflate(R.layout.troll_nhan_layout_2, null);
					Toast toast = new Toast(getActivity());
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(view);
					toast.show();
				}	
			}
		});
		
		
		
		
		btnB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (_data.get(_position).get_dapan().equals("b")) {
					if (_position == 99) {
						Intent iWin = new Intent(getActivity(), WinActivity.class);
						startActivity(iWin);
						getActivity().finish();
					}else {
						FragmentTransaction mFragTransaction = getActivity().getSupportFragmentManager().beginTransaction();
						mFragTransaction.replace(R.id.content_frame,FragmentQuestion.newFragment(_data, _position + 1,_heart,isGoiDien,isYKien,is50,isDoiCauHoi)).commit();
						
						View view = getActivity().getLayoutInflater().inflate(R.layout.giaithich_layout, null);
						TextView tv = (TextView)view.findViewById(R.id.txtGiaiThich);
						String txt = _data.get(_position).get_giaithich();
						Toast toast = new Toast(getActivity());
						if (txt == null) {
						}else {
							tv.setText(txt);
							toast.setDuration(Toast.LENGTH_LONG);
							toast.setView(view);
							toast.show();
						}
					}

				}else {
					if(mp.isPlaying())
			        {  
			            mp.stop();
			            mp.reset();
			        } 
			        try {

			            AssetFileDescriptor afd;
			            afd = getActivity().getAssets().openFd("fai.mp3");
			            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
			            mp.prepare();
			            mp.start();
			        } catch (IllegalStateException e) {
			            e.printStackTrace();
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
					
					_heart--;
					tvSoMang.setText("Fap: "+_heart);
				}
				if (_heart==0) {
					
					Intent iOver = new Intent(getActivity(), GameOverActivity.class);
					startActivity(iOver);
					getActivity().finish();
				}
				
				
				if (_position == 29) {
					View view = getActivity().getLayoutInflater().inflate(R.layout.bua_nhan_layout, null);
					Toast toast = new Toast(getActivity());
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(view);
					toast.show();
				}
				if (_position == 49) {
					View view = getActivity().getLayoutInflater().inflate(R.layout.troll_nhan_layout, null);
					Toast toast = new Toast(getActivity());
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(view);
					toast.show();
				}
				if (_position == 74) {
					View view = getActivity().getLayoutInflater().inflate(R.layout.troll_nhan_layout_2, null);
					Toast toast = new Toast(getActivity());
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(view);
					toast.show();
				}
				
			}
		});
		
		btnC.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (_data.get(_position).get_dapan().equals("c")) {

					if (_position == 99) {
						Intent iWin = new Intent(getActivity(), WinActivity.class);
						startActivity(iWin);
						getActivity().finish();
					}else {
						FragmentTransaction mFragTransaction = getActivity().getSupportFragmentManager().beginTransaction();
						mFragTransaction.replace(R.id.content_frame,FragmentQuestion.newFragment(_data, _position + 1,_heart,isGoiDien,isYKien,is50,isDoiCauHoi)).commit();
						
						View view = getActivity().getLayoutInflater().inflate(R.layout.giaithich_layout, null);
						TextView tv = (TextView)view.findViewById(R.id.txtGiaiThich);
						String txt = _data.get(_position).get_giaithich();
						Toast toast = new Toast(getActivity());
						if (txt == null) {
						}else {
							tv.setText(txt);
							toast.setDuration(Toast.LENGTH_LONG);
							toast.setView(view);
							toast.show();
						}
					}
					

				}else {
					if(mp.isPlaying())
			        {  
			            mp.stop();
			            mp.reset();
			        } 
			        try {

			            AssetFileDescriptor afd;
			            afd = getActivity().getAssets().openFd("fai.mp3");
			            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
			            mp.prepare();
			            mp.start();
			        } catch (IllegalStateException e) {
			            e.printStackTrace();
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
					
					
					_heart--;
					tvSoMang.setText("Fap: "+_heart);
				}
				if (_heart==0) {
					
					Intent iOver = new Intent(getActivity(), GameOverActivity.class);
					startActivity(iOver);
					getActivity().finish();
				}
				
				if (_position == 29) {
					View view = getActivity().getLayoutInflater().inflate(R.layout.bua_nhan_layout, null);
					Toast toast = new Toast(getActivity());
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(view);
					toast.show();
				}
				if (_position == 49) {
					View view = getActivity().getLayoutInflater().inflate(R.layout.troll_nhan_layout, null);
					Toast toast = new Toast(getActivity());
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(view);
					toast.show();
				}
				if (_position == 74) {
					View view = getActivity().getLayoutInflater().inflate(R.layout.troll_nhan_layout_2, null);
					Toast toast = new Toast(getActivity());
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(view);
					toast.show();
				}
				
			}
		});
		
		
		btnD.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (_data.get(_position).get_dapan().equals("d")) {
					
					if (_position == 99) {
						Intent iWin = new Intent(getActivity(), WinActivity.class);
						startActivity(iWin);
						getActivity().finish();
					}else {
						FragmentTransaction mFragTransaction = getActivity().getSupportFragmentManager().beginTransaction();
						mFragTransaction.replace(R.id.content_frame,FragmentQuestion.newFragment(_data, _position + 1,_heart,isGoiDien,isYKien,is50,isDoiCauHoi)).commit();
						
						View view = getActivity().getLayoutInflater().inflate(R.layout.giaithich_layout, null);
						TextView tv = (TextView)view.findViewById(R.id.txtGiaiThich);
						String txt = _data.get(_position).get_giaithich();
						Toast toast = new Toast(getActivity());
						if (txt == null) {
						}else {
							tv.setText(txt);
							toast.setDuration(Toast.LENGTH_LONG);
							toast.setView(view);
							toast.show();
						}
					}
					

				}else {
					
					if(mp.isPlaying())
			        {  
			            mp.stop();
			            mp.reset();
			        } 
			        try {

			            AssetFileDescriptor afd;
			            afd = getActivity().getAssets().openFd("fai.mp3");
			            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
			            mp.prepare();
			            mp.start();
			        } catch (IllegalStateException e) {
			            e.printStackTrace();
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
					
					_heart--;
					tvSoMang.setText("Fap: "+_heart);
				}
				if (_heart==0) {
					Intent iOver = new Intent(getActivity(), GameOverActivity.class);
					startActivity(iOver);
					getActivity().finish();
				}
				
				
				if (_position == 29) {
					View view = getActivity().getLayoutInflater().inflate(R.layout.bua_nhan_layout, null);
					Toast toast = new Toast(getActivity());
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(view);
					toast.show();
				}
				if (_position == 49) {
					View view = getActivity().getLayoutInflater().inflate(R.layout.troll_nhan_layout, null);
					Toast toast = new Toast(getActivity());
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(view);
					toast.show();
				}
				if (_position == 74) {
					View view = getActivity().getLayoutInflater().inflate(R.layout.troll_nhan_layout_2, null);
					Toast toast = new Toast(getActivity());
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(view);
					toast.show();
				}
				
			}
		});
		
		
		

		btnGoiDien.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				isGoiDien = false;
				btnGoiDien.setEnabled(isGoiDien);
				final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
				
				View view = getActivity().getLayoutInflater().inflate(R.layout.goidien_layout, null);
				
				final ImageView billgates = (ImageView)view.findViewById(R.id.imageBillgates);
				final ImageView opama = (ImageView)view.findViewById(R.id.imageOpama);
				final ImageView GSXoay = (ImageView)view.findViewById(R.id.imageGSXOAY);
				final TextView txtCall = (TextView)view.findViewById(R.id.txtCall);
				
				billgates.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						opama.setVisibility(View.GONE);
						GSXoay.setVisibility(View.GONE);
						txtCall.setText(_data.get(_position).get_BillGates());
					}
				});
				opama.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						billgates.setVisibility(View.GONE);
						GSXoay.setVisibility(View.GONE);
						txtCall.setText(_data.get(_position).get_Opama());
					}
				});
				GSXoay.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						billgates.setVisibility(View.GONE);
						opama.setVisibility(View.GONE);
						txtCall.setText(_data.get(_position).get_GSXoay());
					}
				});
				
				alertDialogBuilder.setView(view);
				alertDialogBuilder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				alertDialogBuilder.show();
			}
		});
		
		
		
		
		btnNamMuoiNamMuoi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				is50 = false;
				btnNamMuoiNamMuoi.setEnabled(is50);
				
				String[] nammuoi = _data.get(_position).get_NamMuoiNamMuoi().split("-");
				if (nammuoi[0].equals("a") || nammuoi[1].equals("a")) {
					btnA.setVisibility(View.GONE);
				}
				if (nammuoi[0].equals("b") || nammuoi[1].equals("b")) {
					btnB.setVisibility(View.GONE);
				}
				if (nammuoi[0].equals("c") || nammuoi[1].equals("c")) {
					btnC.setVisibility(View.GONE);
				}
				if (nammuoi[0].equals("d") || nammuoi[1].equals("d")) {
					btnD.setVisibility(View.GONE);
				}
			}
		});
		
		btnDoiCauHoi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				isDoiCauHoi = false;
				btnDoiCauHoi.setEnabled(isDoiCauHoi);
				int PhanTram = _data.get(_position).get_DoiCauHoi();
				
				
				Random rand = new Random();
				int rndNumber = rand.nextInt(99)+1;
				
				if (rndNumber <=PhanTram) {
					View view = getActivity().getLayoutInflater().inflate(R.layout.doicauhoi_layout, null);
					Toast toast = new Toast(getActivity());
					toast.setView(view);
					toast.setDuration(Toast.LENGTH_LONG);
					toast.show();
				}else {
					//Doi Cau Hoi
					FragmentTransaction mFragTransaction = getActivity().getSupportFragmentManager().beginTransaction();
					mFragTransaction.replace(R.id.content_frame,FragmentQuestion.newFragment(_data, _position + 1,_heart,isGoiDien,isYKien,is50,isDoiCauHoi)).commit();
					
				}
				
				
			}
		});
		
		
		btnHoiYKien.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				isYKien = false;
				btnHoiYKien.setEnabled(isYKien);
				
				final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

				View view = getActivity().getLayoutInflater().inflate(R.layout.ykien_layout, null);
				
				final TextView A = (TextView)view.findViewById(R.id.txtPhanA);
				final TextView B = (TextView)view.findViewById(R.id.txtPhanB);
				final TextView C = (TextView)view.findViewById(R.id.txtPhanC);
				final TextView D = (TextView)view.findViewById(R.id.txtPhanD);
				
				String[] traloi = _data.get(_position).get_YKienKhanGia().split("-");
				A.setText("A - "+traloi[0] + "%");
				B.setText("B - "+traloi[1] + "%");
				C.setText("C - "+traloi[2]+ "%");
				D.setText("D - "+traloi[3]+"%");
				
				
				alertDialogBuilder.setView(view);
				alertDialogBuilder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				alertDialogBuilder.show();
			}
		});

	}
	
	
	@Override
	public void onDestroy() {
		mp.stop();
		super.onDestroy();
	}
	
	
	
	
	
	

}

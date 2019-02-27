package mx.unam.enallt.eica1;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private BottomNavigationView mainBottomInicioNavigationView = null;
    private ViewPager mainViewPagerInicio = null;
    private InicioAdapter inicioAdapter = null;
    //Atributo auxiliar saber que elemento fue seleccionado previamente y cual es el actual
    private MenuItem itemSelectedPrevInicio = null;



    public InicioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InicioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =inflater.inflate(R.layout.fragment_inicio, container, false);

        //Enlazar
        mainBottomInicioNavigationView = (BottomNavigationView)v.findViewById(R.id.mainBottomNavigatorInicio);
        mainViewPagerInicio = (ViewPager)v.findViewById(R.id.mainViewPagerInicio);

        //Generar adaptador basado en fragmentos para view pager
        BottomNavigationHelper.removeShiftMode(mainBottomInicioNavigationView);

        inicioAdapter = new InicioAdapter(getChildFragmentManager());
        //Agregar fragmentos que manejara
        inicioAdapter.addFragment(DosCincoFragment.newInstance("",""));
        inicioAdapter.addFragment(DosSeisFragment.newInstance("",""));

        mainViewPagerInicio.setAdapter(inicioAdapter);
        //Implementar su interface para cuando hagan clic

        mainBottomInicioNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dosCincoJunio_item:
                        mainViewPagerInicio.setCurrentItem(0);
                        return true;
                    case R.id.dosSeisJunio_item:
                        mainViewPagerInicio.setCurrentItem(1);
                        return true;
                    default:
                        mainViewPagerInicio.setCurrentItem(0);
                        return true;
                }
            }
        });

        //Para  que al deslizar la pantalla cambien el menu de abajo
        //Escuchador de eventos cuando se desliza

        mainViewPagerInicio.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(itemSelectedPrevInicio != null){
                    itemSelectedPrevInicio.setChecked(false);
                }else{
                    mainBottomInicioNavigationView.getMenu().getItem(0).setChecked(false);

                }
                mainBottomInicioNavigationView.getMenu().getItem(position).setChecked(true);
                itemSelectedPrevInicio=mainBottomInicioNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return v;
    }//onCreateView

}

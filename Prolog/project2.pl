/* CIS 365 Project 2 --- Jaxon Wright and Zack Patterson */

/*--- FACTS ---*/
/*NOTE: some spouse relationships have been ignored*/
spouse(charles_e_wright, angeline_balistreri). /*Jaxons great grandparents*/
child(charles_e_wright, jack_d_wright).  /*Jaxons grandpa*/
child(angeline_balistreri, jack_d_wright).

spouse(merlyn_davis, mildred_hahn).     /*Jaxons great grandparents*/
child(merlyn_davis, vyonne_l_davis).    /*Jaxons grandma*/
child(midred_hahn, vyonne_l_davis).
child(merlyn_davis, shirley_a_davis).   /*Jaxons great aunt*/
child(midred_hahn, shirley_a_davis).
child(merlyn_davis, william_davis).     /*Jaxons great uncle*/
child(midred_hahn, william_davis).
child(merlyn_davis, gerald_davis).      /*Jaxons great uncle*/
child(midred_hahn, gerald_davis).
child(merlyn_davis, jack_e_davis).      /*Jaxons great uncle*/
child(midred_hahn, jack_e_davis).

spouse(jack_d_wright, vyonne_l_davis).  /*Jaxons grandparents*/
child(jack_d_wright, gary_e_wright).    /*Jaxons uncle*/
child(vyonne_l_davis, gary_e_wright).
child(jack_d_wright, daniel_m_wright).  /*Jaxons uncle*/
child(vyonne_l_davis, daniel_m_wright).
child(jack_d_wright, brian_c_wright).   /*Jaxons father*/
child(vyonne_l_davis, brian_c_wright).
child(jack_d_wright, mary_m_wright).    /*Jaxons aunt*/
child(vyonne_l_davis, mary_m_wright).

spouse(brian_c_wright, cindy_a_lubben). /*Jaxons parents*/
child(brian_c_wright, jaxon_h_wright).  /*Jaxon*/
child(brian_c_wright, katie_n_wright).  /*Jaxons half-sister*/
child(cindy_a_lubben, charles_v_vanhorn). /*Jaxons half-brother*/

/*Jaxons first cousins*/
child(gary_e_wright, amy_m_wright).
child(gary_e_wright, sarah_a_wright).
child(daniel_m_wright, abigail_j_wright).
child(daniel_m_wright, lydia_v_wright).
child(daniel_m_wright, sophia_l_wright).
child(daniel_m_wright, angeline_wright).
child(mary_m_wright, milo_lund).
child(mary_m_wright, leo_lund).

/*Jaxons first cousins, once removed */
child(shirley_a_davis, victoria_v_ward).
child(shirley_a_davis, kelly_ward).
child(sarah_a_wright, ava_hanson).
child(sarah_a_wright, leah_hansen).
child(amy_m_wright, elsie_devey).
child(amy_m_wright, clara_devey).
child(lydia_v_wright, erza_bren).

/*Jaxons nieces and nephews*/
child(katie_n_wright, griffin_o_thompson).
child(katie_n_wright, nora_p_thompson).

/*Jaxons second cousins*/
child(kelly_ward, katherine_grzebieniak).
child(victoria_v_ward, mallori_steege).
child(victoria_v_ward, emily_a_steege).
child(victoria_v_ward, madeline_r_steege).
child(victoria_v_ward, adam_steege).


/*--- RULES ---*/
areSiblings(X,Y) :- (child(A,X), child(A,Y)); (child(B,X), child(B,Y)).


/*--- GOALS ---*/
solve :-
  areSiblings(katie_n_wright, jack_d_wright).

?- solve.

/* CIS 365 Project 2 --- Jaxon Wright and Zack Patterson                      */
/* Partial family tree of Jaxon's father's side of the family                 */

/*--- FACTS ------------------------------------------------------------------*/
/* son/daughter(X,Y), Y is a child of X                                       */
/* spouse(MALE,FEMALE)                                                        */

spouse(charles_e_wright, angeline_balistreri).  /*Jaxons great grandparents*/
son(charles_e_wright, jack_d_wright).           /*Jaxons grandpa*/
son(angeline_balistreri, jack_d_wright).

spouse(merlyn_davis, mildred_hahn).             /*Jaxons great grandparents*/
daughter(merlyn_davis, vyonne_l_davis).         /*Jaxons grandma*/
daughter(mildred_hahn, vyonne_l_davis).
daughter(merlyn_davis, shirley_a_davis).        /*Jaxons great aunt*/
daughter(mildred_hahn, shirley_a_davis).
son(merlyn_davis, william_davis).               /*Jaxons great uncle*/
son(mildred_hahn, william_davis).
son(merlyn_davis, gerald_davis).                /*Jaxons great uncle*/
son(mildred_hahn, gerald_davis).
son(merlyn_davis, jack_e_davis).                /*Jaxons great uncle*/
son(mildred_hahn, jack_e_davis).

spouse(jack_d_wright, vyonne_l_davis).          /*Jaxons grandparents*/
son(jack_d_wright, gary_e_wright).              /*Jaxons uncle*/
son(vyonne_l_davis, gary_e_wright).
son(jack_d_wright, daniel_m_wright).            /*Jaxons uncle*/
son(vyonne_l_davis, daniel_m_wright).
son(jack_d_wright, brian_c_wright).             /*Jaxons father*/
son(vyonne_l_davis, brian_c_wright).
daughter(jack_d_wright, mary_m_wright).         /*Jaxons aunt*/
daughter(vyonne_l_davis, mary_m_wright).

spouse(brian_c_wright, cindy_a_lubben). 	      /*Jaxons parents*/
son(brian_c_wright, jaxon_h_wright).  	        /*Jaxon*/
son(cindy_a_lubben, jaxon_h_wright).
daughter(brian_c_wright, katie_n_wright).	      /*Jaxons half-sister*/
son(cindy_a_lubben, charles_v_vanhorn). 	      /*Jaxons half-brother*/

/*Jaxons first cousins*/
daughter(gary_e_wright, amy_m_wright).
daughter(patricia_jones, amy_m_wright).
daughter(gary_e_wright, sarah_a_wright).
daughter(patricia_jones, sarah_a_wright).
daughter(daniel_m_wright, abigail_j_wright).
daughter(kathlene_white, abigail_j_wright).
daughter(daniel_m_wright, lydia_v_wright).
daughter(kathlene_white, lydia_v_wright).
daughter(daniel_m_wright, sophia_l_wright).
daughter(kathlene_white, sophia_l_wright).
daughter(daniel_m_wright, angeline_wright).
daughter(kathlene_white, angeline_wright).
son(mary_m_wright, miles_lund).
son(jon_lund, miles_lund).
son(mary_m_wright, leopold_lund).
son(jon_lund, leopold_lund).
/**their parents*/
spouse(gary_e_wright, patricia_jones).
spouse(daniel_m_wright, kathlene_white).
spouse(jon_lund, mary_m_wright).

spouse(gerald_ward, shirley_a_davis).
/*Jaxons first cousins, once removed */
daughter(shirley_a_davis, victoria_v_ward).
daughter(gerald_ward, victoria_v_ward).
daughter(shirley_a_davis, kelly_ward).
daughter(gerald_ward, kelly_ward).
daughter(sarah_a_wright, ava_hanson).
daughter(cory_hansen, ava_hanson).
daughter(sarah_a_wright, leah_hansen).
daughter(cory_hansen, leah_hansen).
daughter(amy_m_wright, elsie_devey).
daughter(trey_devey, elsie_devey).
daughter(amy_m_wright, clara_devey).
daughter(trey_devey, clara_devey).
son(lydia_v_wright, ezra_bren).
son(cameron_bren, ezra_bren).
/**Their spouses or parents*/
spouse(trey_devey, amy_m_wright).
spouse(craig_steege, victoria_v_ward).
spouse(greg_grzebieniak, kelly_ward).
spouse(cory_hansen, sarah_a_wright).
spouse(cameron_bren, lydia_v_wright).

/*Jaxons nieces and nephews*/
son(katie_n_wright, griffin_o_thompson).
son(alden_o_thompson, griffin_o_thompson).
daughter(katie_n_wright, nora_p_thompson).
daughter(alden_o_thompson, nora_p_thompson).
/**their parents*/
spouse(alden_o_thompson, katie_n_thompson).

/*Jaxons second cousins*/
daughter(kelly_ward, katherine_grzebieniak).
daughter(greg_grzebieniak, katherine_grzebieniak).
daughter(victoria_v_ward, mallori_steege).
daughter(craig_steege, mallori_steege).
daughter(victoria_v_ward, emily_a_steege).
daughter(craig_steege, emily_a_steege).
daughter(victoria_v_ward, madeline_r_steege).
daughter(craig_steege, madeline_r_steege).
son(victoria_v_ward, adam_steege).
son(craig_steege, adam_steege).


/*--- RULES ------------------------------------------------------------------*/
/*Y is a child of X*/
child(X,Y) :- son(X,Y); daughter(X,Y).
/*X and Y are siblings*/
siblings(X,Y) :- child(A,X), child(A,Y), not(X=Y).
/*Y is a descendant of X*/
descend(X,Y) :- child(X,Y).
descend(X,Y) :- child(X,Z), descend(Z,Y).
/*Y is an ancestor of X*/
ascend(X,Y) :- child(Y,X).
ascend(X,Y) :- child(Z,X), ascend(Z,Y).
/*Y is grandparent of X */
grandparent(X,Y) :- child(Y,Z), child(Z,X).
/*Y is great grandparent of X*/
greatgrandparent(X,Y) :- grandparent(Z,Y), child(Z,X).
/*Y is aunt or uncle of X */
auntuncle(X,Y) :- siblings(Y,Z), child(Z,X).
/*Y is great aunt or uncle of X*/
greatauntuncle(X,Y) :- grandparent(X,G), siblings(G,Y).
/*Y is father of X*/
father(X,Y) :- child(Y,X), spouse(Y,A).
/*Y is mother of X*/
mother(X,Y) :- child(Y,X), spouse(A,Y).
/*X and Y are first cousins*/
firstcousins(X,Y) :- siblings(A,B), (child(A,X), child(B,Y); child(B,X), child(A,Y)).
/*Y is Xs first cousin, once removed*/
onceremoved(X,Y) :- (auntuncle(X,A), child(A,C), child(C,Y)); (greatauntuncle(X,A), child(A,Y)).
/*Y is a second cousin of X*/
secondcousin(X,Y) :- onceremoved(X,C), child(C,Y).

/*--- GOALS ------------------------------------------------------------------*/
solve :-
  findall(X,child(jack_d_wright, X),A), nl,
  write(' jack_d_wright\'s children are: '), nl,
  write(A), nl,

  setof(X,siblings(X, brian_c_wright),B), nl,     /*setof removes duplicates*/
  write(' brian_c_wright\'s siblings are: '), nl,
  write(B), nl,

  /*number 2*/
  findall(X,child(X, jaxon_h_wright),C), nl,
  write(' jaxon_h_wright\'s parents are: '), nl,
  write(C), nl,

  /*number 3*/
  findall(X,father(jaxon_h_wright,X),D), nl,
  write(' jaxon_h_wright\'s father is: '), nl,
  write(D), nl,

  /*number 1*/
  findall(X,descend(angeline_balistreri, X),E), nl,
  write(' angeline_balistreri\'s descendants are: '), nl,
  write(E), nl,

  /*number 4*/
  setof(X,auntuncle(jaxon_h_wright, X),F), nl,
  write(' jaxon_h_wright\'s aunts and uncles are: '), nl,
  write(F), nl,

  /*number 5*/
  setof(X,ascend(jaxon_h_wright, X),G), nl,
  write(' jaxon_h_wright\'s ancestors are: '), nl,
  write(G), nl, nl,

  /*number 6*/
  write(' does angeline_wright descend from angeline_balistreri?'), nl,
  (descend(angeline_balistreri, angeline_wright) -> Z = yes; Z = no),
  write(Z), nl,

  /*number 7*/
  setof((X,Y), secondcousin(X,Y),H), nl,
  write(' all pairs of second-cousins:'), nl,
  write(H), nl,

  /*number 8*/
  setof((X,Y), onceremoved(X,Y),I), nl,
  write(' all pairs of first-cousins, once removed:'), nl,
  write(I).

?- solve.

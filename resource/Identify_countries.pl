  %@$&*%		COUNTRY IDENTIFICATION SYSTEM             %@$&*%



%%%%    facts of starting letter of country name......

startswith(u,united_states_america).
startswith(a,afghanistan).
startswith(a,australia).
startswith(b,bangladesh).
startswith(b,bhutan).
startswith(c,china).
startswith(e,england).
startswith(e,egypt).
startswith(f,france).
startswith(f,finlend).
startswith(g,germany).
startswith(i,india).
startswith(i,iceland).
startswith(j,japan).
startswith(m,myanmar).
startswith(n,nepal).
startswith(u,united_arab_emirates).
startswith(p,pakistan).
startswith(r,russia).
startswith(s,switzerland).

%%%%    facts of country currency.......

currency(dollar,united_states_america).
currency(rupee,india).
currency(taka,bangladesh).
currency(afghan_afghani,afghanistan).
currency(bhutanese_ngultrum,bhutan).
currency(yuan,china).
currency(european_euro,england).
currency(european_euro,france).
currency(european_euro,finlend).
currency(rupee,pakistan).
currency(krona,iceland).
currency(rupee,nepal).
currency(dirham,united_arab_emirates).
currency(ruble,russia).
currency(swiss_franc,switzerland).
currency(yen,japan).
currency(myanmar_kyat,myanmar).
currency(egyptian_pound,egypt).
currency(australian_dollar,australia).
currency(european_euro,germany).

%%%%%	 facts of Income rate......

high_Income(france).
high_Income(finlend).
high_Income(iceland).
high_Income(japan).
high_Income(united_arab_emirates).
high_Income(switzerland).
high_Income(united_states_america).
high_Income(england).
high_Income(russia).
high_Income(australia).
high_Income(germany).

lower_middle_Income(bhutan).
lower_middle_Income(india).
lower_middle_Income(pakistan).
lower_middle_Income(myanmar).
lower_middle_Income(egypt).

upper_middle_Income(china).

lower_Income(afghanistan).
lower_Income(bangladesh).
lower_Income(nepal).

%%%%	  facts of main religion.......

muslim(bangladesh).
muslim(afghanistan).
muslim(united_arab_emirates).
muslim(pakistan).
muslim(egypt).

hindu(india).
hindu(bhutan).
hindu(nepal).

christian(united_states_america).
christian(iceland).
christian(china).
christian(finlend).
christian(england).
christian(russia).
christian(switzerland).

buddhist(myanmar).

catholic(france).
catholic(australia).
catholic(germany).

shinto(japan).

%%%%	  facts of continents......

geographical_region(south_southeast_asia,bangladesh).
geographical_region(east_asia,china).
geographical_region(south_southeast_asia,india).
geographical_region(west_central_asia,pakistan).
geographical_region(north_central_america,united_states_america).
geographical_region(east_asia,japan).
geographical_region(europian_union,france).
geographical_region(europe,iceland).
geographical_region(europian_union,finlend).
geographical_region(north_asia,russia).
geographical_region(west_central_asia,united_arab_emirates).
geographical_region(europian_union,england).
geographical_region(south_southeast_asia,nepal).
geographical_region(south_southeast_asia,myanmar).
geographical_region(europe,switzerland).
geographical_region(south_southeast_asia,bhutan).
geographical_region(west_central_asia,afghanistan).
geographical_region(north_america,egypt).
geographical_region(oceania,australia).
geographical_region(europe,germany).



%%%%     facts of unemployment rate........

unemployment_rate(about_5,myanmar).
unemployment_rate(about_8,egypt).
unemployment_rate(about_6,australia).
unemployment_rate(about_4,germany).
unemployment_rate(about_5,united_states_america).
unemployment_rate(about_8,afghanistan).
unemployment_rate(about_3,india).
unemployment_rate(about_2,bhutan).
unemployment_rate(about_4,bangladesh).
unemployment_rate(about_5,china).
unemployment_rate(about_5,england).
unemployment_rate(about_10,france).
unemployment_rate(about_9,finlend).
unemployment_rate(about_4,iceland).
unemployment_rate(about_3,nepal).
unemployment_rate(about_6,pakistan).
unemployment_rate(about_4,united_arab_emirates).
unemployment_rate(about_6,russia).
unemployment_rate(about_5,switzerland).
unemployment_rate(about_3,japan).

%%%%    rules to catagorize the countries by main religion......

main_religion(muslim,X):-
	muslim(X).
main_religion(hindu,X):-
	hindu(X).
main_religion(christian,X):-
	christian(X).
main_religion(shinto,X):-
	shinto(X).
main_religion(catholic,X):-
	catholic(X).
main_religion(buddhist,X):-
	buddhist(X).


%%%%    rules for catagorizing income rate.......

income_rate(high_Income,X):-
	high_Income(X).
income_rate(upper_middle_Income,X):-
	upper_middle_Income(X).
income_rate(lower_middle_Income,X):-
	lower_middle_Income(X).
income_rate(lower_Income,X):-
	lower_Income(X).


%%%%   rules for identifing which countries developed??.....

developed(X):-
	income_rate(high_Income,X).

developing(X):-
	income_rate(upper_middle_Income,X);
	unemployment_rate(about_4,X),income_rate(lower_Income,X);
	unemployment_rate(about_3,X),income_rate(lower_Income,X);
	income_rate(lower_middle_Income,X).

not(developing(X)):-
	unemployment_rate(about_8,X).

underdeveloped(X):-
	income_rate(lower_Income,X),
	not(developing(X)).

%%%%   rules to identify continents......

continent(asia,X):-
	geographical_region(west_central_asia,X);
	geographical_region(east_asia,X);
	geographical_region(north_asia,X);
	geographical_region(south_southeast_asia,X).

continent(north_central_america,X):-
	geographical_region(north_central_america,X);
	geographical_region(north_america,X);
	geographical_region(caribbean_islands,X);
	geographical_region(mesoamerica,X)
	.

continent(europe,X):-
	geographical_region(europian_union,X);
	geographical_region(europe,X).

continent(oceania,X):-
	geographical_region(oceania,X).

continent(africa,X):-
	geographical_region(north_africa,X);
	geographical_region(eastern_africa,X);
	geographical_region(middle_africa,X);
	geographical_region(western_africa,X);
	geographical_region(southern_africa,X).

continent(antarctic,X):-
	geographical_region(antartic,X).


%%%%    rules to catagorize the weather......

moist(X):-
	geographical_region(south_southeast_asia,X);
	geographical_region(east_asia,X).
hot(X):-
	geographical_region(west_central_asia,X);
	geographical_region(southern_africa,X);
	geographical_region(north_america,X);
	geographical_region(south_america,X);
	geographical_region(west_asia,X).
cold(X):-
	geographical_region(north_asia,X);
	geographical_region(antarctic,X).

dry(X):-
	geographical_region(west_africa,X);
	geographical_region(east_africa,X).
extreme_hot_cold(X):-
	geographical_region(europe,X);
	geographical_region(europian_union,X).


%%%%   rules for query weather......

weather(cold,X):-
	cold(X).

weather(hot,X):-
	hot(X).

weather(extreme_mixed,X):-
	extreme_hot_cold(X).

weather(moist,X):-
	moist(X).

weather(dry,X):-
	dry(X).



%%%%  \\\\	$$$      @ READY FOR QUERY @     $$$    ////   %%%%



                               % THE END %

package es.um.nosql.schemainference.dslparameter.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import es.um.nosql.schemainference.dslparameter.services.ODMParameterGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalODMParameterParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'ODMParameters'", "'{'", "'mapper'", "':'", "'}'", "'discriminator'", "'Entity'", "'validators'", "','", "'uniques'", "'updates'", "'indexes'", "'('", "')'", "'->'", "'kind:'", "'Sorted'", "'Hashed'"
    };
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=5;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=6;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalODMParameterParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalODMParameterParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalODMParameterParser.tokenNames; }
    public String getGrammarFileName() { return "InternalODMParameter.g"; }



     	private ODMParameterGrammarAccess grammarAccess;

        public InternalODMParameterParser(TokenStream input, ODMParameterGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "MongooseModel";
       	}

       	@Override
       	protected ODMParameterGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleMongooseModel"
    // InternalODMParameter.g:65:1: entryRuleMongooseModel returns [EObject current=null] : iv_ruleMongooseModel= ruleMongooseModel EOF ;
    public final EObject entryRuleMongooseModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMongooseModel = null;


        try {
            // InternalODMParameter.g:65:54: (iv_ruleMongooseModel= ruleMongooseModel EOF )
            // InternalODMParameter.g:66:2: iv_ruleMongooseModel= ruleMongooseModel EOF
            {
             newCompositeNode(grammarAccess.getMongooseModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMongooseModel=ruleMongooseModel();

            state._fsp--;

             current =iv_ruleMongooseModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMongooseModel"


    // $ANTLR start "ruleMongooseModel"
    // InternalODMParameter.g:72:1: ruleMongooseModel returns [EObject current=null] : (otherlv_0= 'ODMParameters' otherlv_1= '{' otherlv_2= 'mapper' otherlv_3= ':' ( (lv_mapper_4_0= ruleEString ) ) ( (lv_parameters_5_0= ruleEntity ) )+ otherlv_6= '}' ) ;
    public final EObject ruleMongooseModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_6=null;
        AntlrDatatypeRuleToken lv_mapper_4_0 = null;

        EObject lv_parameters_5_0 = null;



        	enterRule();

        try {
            // InternalODMParameter.g:78:2: ( (otherlv_0= 'ODMParameters' otherlv_1= '{' otherlv_2= 'mapper' otherlv_3= ':' ( (lv_mapper_4_0= ruleEString ) ) ( (lv_parameters_5_0= ruleEntity ) )+ otherlv_6= '}' ) )
            // InternalODMParameter.g:79:2: (otherlv_0= 'ODMParameters' otherlv_1= '{' otherlv_2= 'mapper' otherlv_3= ':' ( (lv_mapper_4_0= ruleEString ) ) ( (lv_parameters_5_0= ruleEntity ) )+ otherlv_6= '}' )
            {
            // InternalODMParameter.g:79:2: (otherlv_0= 'ODMParameters' otherlv_1= '{' otherlv_2= 'mapper' otherlv_3= ':' ( (lv_mapper_4_0= ruleEString ) ) ( (lv_parameters_5_0= ruleEntity ) )+ otherlv_6= '}' )
            // InternalODMParameter.g:80:3: otherlv_0= 'ODMParameters' otherlv_1= '{' otherlv_2= 'mapper' otherlv_3= ':' ( (lv_mapper_4_0= ruleEString ) ) ( (lv_parameters_5_0= ruleEntity ) )+ otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getMongooseModelAccess().getODMParametersKeyword_0());
            		
            otherlv_1=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getMongooseModelAccess().getLeftCurlyBracketKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_5); 

            			newLeafNode(otherlv_2, grammarAccess.getMongooseModelAccess().getMapperKeyword_2());
            		
            otherlv_3=(Token)match(input,14,FOLLOW_6); 

            			newLeafNode(otherlv_3, grammarAccess.getMongooseModelAccess().getColonKeyword_3());
            		
            // InternalODMParameter.g:96:3: ( (lv_mapper_4_0= ruleEString ) )
            // InternalODMParameter.g:97:4: (lv_mapper_4_0= ruleEString )
            {
            // InternalODMParameter.g:97:4: (lv_mapper_4_0= ruleEString )
            // InternalODMParameter.g:98:5: lv_mapper_4_0= ruleEString
            {

            					newCompositeNode(grammarAccess.getMongooseModelAccess().getMapperEStringParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_7);
            lv_mapper_4_0=ruleEString();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMongooseModelRule());
            					}
            					set(
            						current,
            						"mapper",
            						lv_mapper_4_0,
            						"es.um.nosql.schemainference.dslparameter.ODMParameter.EString");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalODMParameter.g:115:3: ( (lv_parameters_5_0= ruleEntity ) )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=16 && LA1_0<=17)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalODMParameter.g:116:4: (lv_parameters_5_0= ruleEntity )
            	    {
            	    // InternalODMParameter.g:116:4: (lv_parameters_5_0= ruleEntity )
            	    // InternalODMParameter.g:117:5: lv_parameters_5_0= ruleEntity
            	    {

            	    					newCompositeNode(grammarAccess.getMongooseModelAccess().getParametersEntityParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_8);
            	    lv_parameters_5_0=ruleEntity();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getMongooseModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"parameters",
            	    						lv_parameters_5_0,
            	    						"es.um.nosql.schemainference.dslparameter.ODMParameter.Entity");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            otherlv_6=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getMongooseModelAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMongooseModel"


    // $ANTLR start "entryRuleEntity"
    // InternalODMParameter.g:142:1: entryRuleEntity returns [EObject current=null] : iv_ruleEntity= ruleEntity EOF ;
    public final EObject entryRuleEntity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntity = null;


        try {
            // InternalODMParameter.g:142:47: (iv_ruleEntity= ruleEntity EOF )
            // InternalODMParameter.g:143:2: iv_ruleEntity= ruleEntity EOF
            {
             newCompositeNode(grammarAccess.getEntityRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEntity=ruleEntity();

            state._fsp--;

             current =iv_ruleEntity; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEntity"


    // $ANTLR start "ruleEntity"
    // InternalODMParameter.g:149:1: ruleEntity returns [EObject current=null] : ( () ( (lv_discriminator_1_0= 'discriminator' ) )? otherlv_2= 'Entity' ( (lv_name_3_0= ruleEString ) ) otherlv_4= '{' (otherlv_5= 'validators' otherlv_6= '{' ( (lv_validators_7_0= ruleValidator ) ) (otherlv_8= ',' ( (lv_validators_9_0= ruleValidator ) ) )* otherlv_10= '}' )? (otherlv_11= 'uniques' otherlv_12= '{' ( (lv_uniques_13_0= ruleUnique ) ) (otherlv_14= ',' ( (lv_uniques_15_0= ruleUnique ) ) )* otherlv_16= '}' )? (otherlv_17= 'updates' otherlv_18= '{' ( (lv_updates_19_0= ruleUpdate ) ) (otherlv_20= ',' ( (lv_updates_21_0= ruleUpdate ) ) )* otherlv_22= '}' )? (otherlv_23= 'indexes' otherlv_24= '{' ( (lv_indexes_25_0= ruleIndex ) ) (otherlv_26= ',' ( (lv_indexes_27_0= ruleIndex ) ) )* otherlv_28= '}' )? otherlv_29= '}' ) ;
    public final EObject ruleEntity() throws RecognitionException {
        EObject current = null;

        Token lv_discriminator_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        Token otherlv_24=null;
        Token otherlv_26=null;
        Token otherlv_28=null;
        Token otherlv_29=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;

        EObject lv_validators_7_0 = null;

        EObject lv_validators_9_0 = null;

        EObject lv_uniques_13_0 = null;

        EObject lv_uniques_15_0 = null;

        EObject lv_updates_19_0 = null;

        EObject lv_updates_21_0 = null;

        EObject lv_indexes_25_0 = null;

        EObject lv_indexes_27_0 = null;



        	enterRule();

        try {
            // InternalODMParameter.g:155:2: ( ( () ( (lv_discriminator_1_0= 'discriminator' ) )? otherlv_2= 'Entity' ( (lv_name_3_0= ruleEString ) ) otherlv_4= '{' (otherlv_5= 'validators' otherlv_6= '{' ( (lv_validators_7_0= ruleValidator ) ) (otherlv_8= ',' ( (lv_validators_9_0= ruleValidator ) ) )* otherlv_10= '}' )? (otherlv_11= 'uniques' otherlv_12= '{' ( (lv_uniques_13_0= ruleUnique ) ) (otherlv_14= ',' ( (lv_uniques_15_0= ruleUnique ) ) )* otherlv_16= '}' )? (otherlv_17= 'updates' otherlv_18= '{' ( (lv_updates_19_0= ruleUpdate ) ) (otherlv_20= ',' ( (lv_updates_21_0= ruleUpdate ) ) )* otherlv_22= '}' )? (otherlv_23= 'indexes' otherlv_24= '{' ( (lv_indexes_25_0= ruleIndex ) ) (otherlv_26= ',' ( (lv_indexes_27_0= ruleIndex ) ) )* otherlv_28= '}' )? otherlv_29= '}' ) )
            // InternalODMParameter.g:156:2: ( () ( (lv_discriminator_1_0= 'discriminator' ) )? otherlv_2= 'Entity' ( (lv_name_3_0= ruleEString ) ) otherlv_4= '{' (otherlv_5= 'validators' otherlv_6= '{' ( (lv_validators_7_0= ruleValidator ) ) (otherlv_8= ',' ( (lv_validators_9_0= ruleValidator ) ) )* otherlv_10= '}' )? (otherlv_11= 'uniques' otherlv_12= '{' ( (lv_uniques_13_0= ruleUnique ) ) (otherlv_14= ',' ( (lv_uniques_15_0= ruleUnique ) ) )* otherlv_16= '}' )? (otherlv_17= 'updates' otherlv_18= '{' ( (lv_updates_19_0= ruleUpdate ) ) (otherlv_20= ',' ( (lv_updates_21_0= ruleUpdate ) ) )* otherlv_22= '}' )? (otherlv_23= 'indexes' otherlv_24= '{' ( (lv_indexes_25_0= ruleIndex ) ) (otherlv_26= ',' ( (lv_indexes_27_0= ruleIndex ) ) )* otherlv_28= '}' )? otherlv_29= '}' )
            {
            // InternalODMParameter.g:156:2: ( () ( (lv_discriminator_1_0= 'discriminator' ) )? otherlv_2= 'Entity' ( (lv_name_3_0= ruleEString ) ) otherlv_4= '{' (otherlv_5= 'validators' otherlv_6= '{' ( (lv_validators_7_0= ruleValidator ) ) (otherlv_8= ',' ( (lv_validators_9_0= ruleValidator ) ) )* otherlv_10= '}' )? (otherlv_11= 'uniques' otherlv_12= '{' ( (lv_uniques_13_0= ruleUnique ) ) (otherlv_14= ',' ( (lv_uniques_15_0= ruleUnique ) ) )* otherlv_16= '}' )? (otherlv_17= 'updates' otherlv_18= '{' ( (lv_updates_19_0= ruleUpdate ) ) (otherlv_20= ',' ( (lv_updates_21_0= ruleUpdate ) ) )* otherlv_22= '}' )? (otherlv_23= 'indexes' otherlv_24= '{' ( (lv_indexes_25_0= ruleIndex ) ) (otherlv_26= ',' ( (lv_indexes_27_0= ruleIndex ) ) )* otherlv_28= '}' )? otherlv_29= '}' )
            // InternalODMParameter.g:157:3: () ( (lv_discriminator_1_0= 'discriminator' ) )? otherlv_2= 'Entity' ( (lv_name_3_0= ruleEString ) ) otherlv_4= '{' (otherlv_5= 'validators' otherlv_6= '{' ( (lv_validators_7_0= ruleValidator ) ) (otherlv_8= ',' ( (lv_validators_9_0= ruleValidator ) ) )* otherlv_10= '}' )? (otherlv_11= 'uniques' otherlv_12= '{' ( (lv_uniques_13_0= ruleUnique ) ) (otherlv_14= ',' ( (lv_uniques_15_0= ruleUnique ) ) )* otherlv_16= '}' )? (otherlv_17= 'updates' otherlv_18= '{' ( (lv_updates_19_0= ruleUpdate ) ) (otherlv_20= ',' ( (lv_updates_21_0= ruleUpdate ) ) )* otherlv_22= '}' )? (otherlv_23= 'indexes' otherlv_24= '{' ( (lv_indexes_25_0= ruleIndex ) ) (otherlv_26= ',' ( (lv_indexes_27_0= ruleIndex ) ) )* otherlv_28= '}' )? otherlv_29= '}'
            {
            // InternalODMParameter.g:157:3: ()
            // InternalODMParameter.g:158:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getEntityAccess().getEntityParameterAction_0(),
            					current);
            			

            }

            // InternalODMParameter.g:164:3: ( (lv_discriminator_1_0= 'discriminator' ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==16) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalODMParameter.g:165:4: (lv_discriminator_1_0= 'discriminator' )
                    {
                    // InternalODMParameter.g:165:4: (lv_discriminator_1_0= 'discriminator' )
                    // InternalODMParameter.g:166:5: lv_discriminator_1_0= 'discriminator'
                    {
                    lv_discriminator_1_0=(Token)match(input,16,FOLLOW_9); 

                    					newLeafNode(lv_discriminator_1_0, grammarAccess.getEntityAccess().getDiscriminatorDiscriminatorKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getEntityRule());
                    					}
                    					setWithLastConsumed(current, "discriminator", true, "discriminator");
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,17,FOLLOW_6); 

            			newLeafNode(otherlv_2, grammarAccess.getEntityAccess().getEntityKeyword_2());
            		
            // InternalODMParameter.g:182:3: ( (lv_name_3_0= ruleEString ) )
            // InternalODMParameter.g:183:4: (lv_name_3_0= ruleEString )
            {
            // InternalODMParameter.g:183:4: (lv_name_3_0= ruleEString )
            // InternalODMParameter.g:184:5: lv_name_3_0= ruleEString
            {

            					newCompositeNode(grammarAccess.getEntityAccess().getNameEStringParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_3);
            lv_name_3_0=ruleEString();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getEntityRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_3_0,
            						"es.um.nosql.schemainference.dslparameter.ODMParameter.EString");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,12,FOLLOW_10); 

            			newLeafNode(otherlv_4, grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalODMParameter.g:205:3: (otherlv_5= 'validators' otherlv_6= '{' ( (lv_validators_7_0= ruleValidator ) ) (otherlv_8= ',' ( (lv_validators_9_0= ruleValidator ) ) )* otherlv_10= '}' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==18) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalODMParameter.g:206:4: otherlv_5= 'validators' otherlv_6= '{' ( (lv_validators_7_0= ruleValidator ) ) (otherlv_8= ',' ( (lv_validators_9_0= ruleValidator ) ) )* otherlv_10= '}'
                    {
                    otherlv_5=(Token)match(input,18,FOLLOW_3); 

                    				newLeafNode(otherlv_5, grammarAccess.getEntityAccess().getValidatorsKeyword_5_0());
                    			
                    otherlv_6=(Token)match(input,12,FOLLOW_11); 

                    				newLeafNode(otherlv_6, grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_5_1());
                    			
                    // InternalODMParameter.g:214:4: ( (lv_validators_7_0= ruleValidator ) )
                    // InternalODMParameter.g:215:5: (lv_validators_7_0= ruleValidator )
                    {
                    // InternalODMParameter.g:215:5: (lv_validators_7_0= ruleValidator )
                    // InternalODMParameter.g:216:6: lv_validators_7_0= ruleValidator
                    {

                    						newCompositeNode(grammarAccess.getEntityAccess().getValidatorsValidatorParserRuleCall_5_2_0());
                    					
                    pushFollow(FOLLOW_12);
                    lv_validators_7_0=ruleValidator();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getEntityRule());
                    						}
                    						add(
                    							current,
                    							"validators",
                    							lv_validators_7_0,
                    							"es.um.nosql.schemainference.dslparameter.ODMParameter.Validator");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalODMParameter.g:233:4: (otherlv_8= ',' ( (lv_validators_9_0= ruleValidator ) ) )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==19) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // InternalODMParameter.g:234:5: otherlv_8= ',' ( (lv_validators_9_0= ruleValidator ) )
                    	    {
                    	    otherlv_8=(Token)match(input,19,FOLLOW_11); 

                    	    					newLeafNode(otherlv_8, grammarAccess.getEntityAccess().getCommaKeyword_5_3_0());
                    	    				
                    	    // InternalODMParameter.g:238:5: ( (lv_validators_9_0= ruleValidator ) )
                    	    // InternalODMParameter.g:239:6: (lv_validators_9_0= ruleValidator )
                    	    {
                    	    // InternalODMParameter.g:239:6: (lv_validators_9_0= ruleValidator )
                    	    // InternalODMParameter.g:240:7: lv_validators_9_0= ruleValidator
                    	    {

                    	    							newCompositeNode(grammarAccess.getEntityAccess().getValidatorsValidatorParserRuleCall_5_3_1_0());
                    	    						
                    	    pushFollow(FOLLOW_12);
                    	    lv_validators_9_0=ruleValidator();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getEntityRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"validators",
                    	    								lv_validators_9_0,
                    	    								"es.um.nosql.schemainference.dslparameter.ODMParameter.Validator");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);

                    otherlv_10=(Token)match(input,15,FOLLOW_13); 

                    				newLeafNode(otherlv_10, grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_5_4());
                    			

                    }
                    break;

            }

            // InternalODMParameter.g:263:3: (otherlv_11= 'uniques' otherlv_12= '{' ( (lv_uniques_13_0= ruleUnique ) ) (otherlv_14= ',' ( (lv_uniques_15_0= ruleUnique ) ) )* otherlv_16= '}' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==20) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalODMParameter.g:264:4: otherlv_11= 'uniques' otherlv_12= '{' ( (lv_uniques_13_0= ruleUnique ) ) (otherlv_14= ',' ( (lv_uniques_15_0= ruleUnique ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,20,FOLLOW_3); 

                    				newLeafNode(otherlv_11, grammarAccess.getEntityAccess().getUniquesKeyword_6_0());
                    			
                    otherlv_12=(Token)match(input,12,FOLLOW_14); 

                    				newLeafNode(otherlv_12, grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_6_1());
                    			
                    // InternalODMParameter.g:272:4: ( (lv_uniques_13_0= ruleUnique ) )
                    // InternalODMParameter.g:273:5: (lv_uniques_13_0= ruleUnique )
                    {
                    // InternalODMParameter.g:273:5: (lv_uniques_13_0= ruleUnique )
                    // InternalODMParameter.g:274:6: lv_uniques_13_0= ruleUnique
                    {

                    						newCompositeNode(grammarAccess.getEntityAccess().getUniquesUniqueParserRuleCall_6_2_0());
                    					
                    pushFollow(FOLLOW_12);
                    lv_uniques_13_0=ruleUnique();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getEntityRule());
                    						}
                    						add(
                    							current,
                    							"uniques",
                    							lv_uniques_13_0,
                    							"es.um.nosql.schemainference.dslparameter.ODMParameter.Unique");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalODMParameter.g:291:4: (otherlv_14= ',' ( (lv_uniques_15_0= ruleUnique ) ) )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==19) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // InternalODMParameter.g:292:5: otherlv_14= ',' ( (lv_uniques_15_0= ruleUnique ) )
                    	    {
                    	    otherlv_14=(Token)match(input,19,FOLLOW_14); 

                    	    					newLeafNode(otherlv_14, grammarAccess.getEntityAccess().getCommaKeyword_6_3_0());
                    	    				
                    	    // InternalODMParameter.g:296:5: ( (lv_uniques_15_0= ruleUnique ) )
                    	    // InternalODMParameter.g:297:6: (lv_uniques_15_0= ruleUnique )
                    	    {
                    	    // InternalODMParameter.g:297:6: (lv_uniques_15_0= ruleUnique )
                    	    // InternalODMParameter.g:298:7: lv_uniques_15_0= ruleUnique
                    	    {

                    	    							newCompositeNode(grammarAccess.getEntityAccess().getUniquesUniqueParserRuleCall_6_3_1_0());
                    	    						
                    	    pushFollow(FOLLOW_12);
                    	    lv_uniques_15_0=ruleUnique();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getEntityRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"uniques",
                    	    								lv_uniques_15_0,
                    	    								"es.um.nosql.schemainference.dslparameter.ODMParameter.Unique");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,15,FOLLOW_15); 

                    				newLeafNode(otherlv_16, grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_6_4());
                    			

                    }
                    break;

            }

            // InternalODMParameter.g:321:3: (otherlv_17= 'updates' otherlv_18= '{' ( (lv_updates_19_0= ruleUpdate ) ) (otherlv_20= ',' ( (lv_updates_21_0= ruleUpdate ) ) )* otherlv_22= '}' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==21) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalODMParameter.g:322:4: otherlv_17= 'updates' otherlv_18= '{' ( (lv_updates_19_0= ruleUpdate ) ) (otherlv_20= ',' ( (lv_updates_21_0= ruleUpdate ) ) )* otherlv_22= '}'
                    {
                    otherlv_17=(Token)match(input,21,FOLLOW_3); 

                    				newLeafNode(otherlv_17, grammarAccess.getEntityAccess().getUpdatesKeyword_7_0());
                    			
                    otherlv_18=(Token)match(input,12,FOLLOW_6); 

                    				newLeafNode(otherlv_18, grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_7_1());
                    			
                    // InternalODMParameter.g:330:4: ( (lv_updates_19_0= ruleUpdate ) )
                    // InternalODMParameter.g:331:5: (lv_updates_19_0= ruleUpdate )
                    {
                    // InternalODMParameter.g:331:5: (lv_updates_19_0= ruleUpdate )
                    // InternalODMParameter.g:332:6: lv_updates_19_0= ruleUpdate
                    {

                    						newCompositeNode(grammarAccess.getEntityAccess().getUpdatesUpdateParserRuleCall_7_2_0());
                    					
                    pushFollow(FOLLOW_12);
                    lv_updates_19_0=ruleUpdate();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getEntityRule());
                    						}
                    						add(
                    							current,
                    							"updates",
                    							lv_updates_19_0,
                    							"es.um.nosql.schemainference.dslparameter.ODMParameter.Update");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalODMParameter.g:349:4: (otherlv_20= ',' ( (lv_updates_21_0= ruleUpdate ) ) )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==19) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // InternalODMParameter.g:350:5: otherlv_20= ',' ( (lv_updates_21_0= ruleUpdate ) )
                    	    {
                    	    otherlv_20=(Token)match(input,19,FOLLOW_6); 

                    	    					newLeafNode(otherlv_20, grammarAccess.getEntityAccess().getCommaKeyword_7_3_0());
                    	    				
                    	    // InternalODMParameter.g:354:5: ( (lv_updates_21_0= ruleUpdate ) )
                    	    // InternalODMParameter.g:355:6: (lv_updates_21_0= ruleUpdate )
                    	    {
                    	    // InternalODMParameter.g:355:6: (lv_updates_21_0= ruleUpdate )
                    	    // InternalODMParameter.g:356:7: lv_updates_21_0= ruleUpdate
                    	    {

                    	    							newCompositeNode(grammarAccess.getEntityAccess().getUpdatesUpdateParserRuleCall_7_3_1_0());
                    	    						
                    	    pushFollow(FOLLOW_12);
                    	    lv_updates_21_0=ruleUpdate();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getEntityRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"updates",
                    	    								lv_updates_21_0,
                    	    								"es.um.nosql.schemainference.dslparameter.ODMParameter.Update");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    otherlv_22=(Token)match(input,15,FOLLOW_16); 

                    				newLeafNode(otherlv_22, grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_7_4());
                    			

                    }
                    break;

            }

            // InternalODMParameter.g:379:3: (otherlv_23= 'indexes' otherlv_24= '{' ( (lv_indexes_25_0= ruleIndex ) ) (otherlv_26= ',' ( (lv_indexes_27_0= ruleIndex ) ) )* otherlv_28= '}' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==22) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalODMParameter.g:380:4: otherlv_23= 'indexes' otherlv_24= '{' ( (lv_indexes_25_0= ruleIndex ) ) (otherlv_26= ',' ( (lv_indexes_27_0= ruleIndex ) ) )* otherlv_28= '}'
                    {
                    otherlv_23=(Token)match(input,22,FOLLOW_3); 

                    				newLeafNode(otherlv_23, grammarAccess.getEntityAccess().getIndexesKeyword_8_0());
                    			
                    otherlv_24=(Token)match(input,12,FOLLOW_17); 

                    				newLeafNode(otherlv_24, grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_8_1());
                    			
                    // InternalODMParameter.g:388:4: ( (lv_indexes_25_0= ruleIndex ) )
                    // InternalODMParameter.g:389:5: (lv_indexes_25_0= ruleIndex )
                    {
                    // InternalODMParameter.g:389:5: (lv_indexes_25_0= ruleIndex )
                    // InternalODMParameter.g:390:6: lv_indexes_25_0= ruleIndex
                    {

                    						newCompositeNode(grammarAccess.getEntityAccess().getIndexesIndexParserRuleCall_8_2_0());
                    					
                    pushFollow(FOLLOW_12);
                    lv_indexes_25_0=ruleIndex();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getEntityRule());
                    						}
                    						add(
                    							current,
                    							"indexes",
                    							lv_indexes_25_0,
                    							"es.um.nosql.schemainference.dslparameter.ODMParameter.Index");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalODMParameter.g:407:4: (otherlv_26= ',' ( (lv_indexes_27_0= ruleIndex ) ) )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==19) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // InternalODMParameter.g:408:5: otherlv_26= ',' ( (lv_indexes_27_0= ruleIndex ) )
                    	    {
                    	    otherlv_26=(Token)match(input,19,FOLLOW_17); 

                    	    					newLeafNode(otherlv_26, grammarAccess.getEntityAccess().getCommaKeyword_8_3_0());
                    	    				
                    	    // InternalODMParameter.g:412:5: ( (lv_indexes_27_0= ruleIndex ) )
                    	    // InternalODMParameter.g:413:6: (lv_indexes_27_0= ruleIndex )
                    	    {
                    	    // InternalODMParameter.g:413:6: (lv_indexes_27_0= ruleIndex )
                    	    // InternalODMParameter.g:414:7: lv_indexes_27_0= ruleIndex
                    	    {

                    	    							newCompositeNode(grammarAccess.getEntityAccess().getIndexesIndexParserRuleCall_8_3_1_0());
                    	    						
                    	    pushFollow(FOLLOW_12);
                    	    lv_indexes_27_0=ruleIndex();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getEntityRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"indexes",
                    	    								lv_indexes_27_0,
                    	    								"es.um.nosql.schemainference.dslparameter.ODMParameter.Index");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);

                    otherlv_28=(Token)match(input,15,FOLLOW_18); 

                    				newLeafNode(otherlv_28, grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_8_4());
                    			

                    }
                    break;

            }

            otherlv_29=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_29, grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_9());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEntity"


    // $ANTLR start "entryRuleEString"
    // InternalODMParameter.g:445:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // InternalODMParameter.g:445:47: (iv_ruleEString= ruleEString EOF )
            // InternalODMParameter.g:446:2: iv_ruleEString= ruleEString EOF
            {
             newCompositeNode(grammarAccess.getEStringRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEString=ruleEString();

            state._fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEString"


    // $ANTLR start "ruleEString"
    // InternalODMParameter.g:452:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;


        	enterRule();

        try {
            // InternalODMParameter.g:458:2: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // InternalODMParameter.g:459:2: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // InternalODMParameter.g:459:2: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_STRING) ) {
                alt11=1;
            }
            else if ( (LA11_0==RULE_ID) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // InternalODMParameter.g:460:3: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    			current.merge(this_STRING_0);
                    		

                    			newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalODMParameter.g:468:3: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)match(input,RULE_ID,FOLLOW_2); 

                    			current.merge(this_ID_1);
                    		

                    			newLeafNode(this_ID_1, grammarAccess.getEStringAccess().getIDTerminalRuleCall_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEString"


    // $ANTLR start "entryRuleValidator"
    // InternalODMParameter.g:479:1: entryRuleValidator returns [EObject current=null] : iv_ruleValidator= ruleValidator EOF ;
    public final EObject entryRuleValidator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValidator = null;


        try {
            // InternalODMParameter.g:479:50: (iv_ruleValidator= ruleValidator EOF )
            // InternalODMParameter.g:480:2: iv_ruleValidator= ruleValidator EOF
            {
             newCompositeNode(grammarAccess.getValidatorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleValidator=ruleValidator();

            state._fsp--;

             current =iv_ruleValidator; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValidator"


    // $ANTLR start "ruleValidator"
    // InternalODMParameter.g:486:1: ruleValidator returns [EObject current=null] : ( () otherlv_1= '(' ( (lv_fieldName_2_0= ruleEString ) ) otherlv_3= ':' ( (lv_ValidatorName_4_0= ruleEString ) )? otherlv_5= ')' ) ;
    public final EObject ruleValidator() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_fieldName_2_0 = null;

        AntlrDatatypeRuleToken lv_ValidatorName_4_0 = null;



        	enterRule();

        try {
            // InternalODMParameter.g:492:2: ( ( () otherlv_1= '(' ( (lv_fieldName_2_0= ruleEString ) ) otherlv_3= ':' ( (lv_ValidatorName_4_0= ruleEString ) )? otherlv_5= ')' ) )
            // InternalODMParameter.g:493:2: ( () otherlv_1= '(' ( (lv_fieldName_2_0= ruleEString ) ) otherlv_3= ':' ( (lv_ValidatorName_4_0= ruleEString ) )? otherlv_5= ')' )
            {
            // InternalODMParameter.g:493:2: ( () otherlv_1= '(' ( (lv_fieldName_2_0= ruleEString ) ) otherlv_3= ':' ( (lv_ValidatorName_4_0= ruleEString ) )? otherlv_5= ')' )
            // InternalODMParameter.g:494:3: () otherlv_1= '(' ( (lv_fieldName_2_0= ruleEString ) ) otherlv_3= ':' ( (lv_ValidatorName_4_0= ruleEString ) )? otherlv_5= ')'
            {
            // InternalODMParameter.g:494:3: ()
            // InternalODMParameter.g:495:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getValidatorAccess().getValidatorAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,23,FOLLOW_6); 

            			newLeafNode(otherlv_1, grammarAccess.getValidatorAccess().getLeftParenthesisKeyword_1());
            		
            // InternalODMParameter.g:505:3: ( (lv_fieldName_2_0= ruleEString ) )
            // InternalODMParameter.g:506:4: (lv_fieldName_2_0= ruleEString )
            {
            // InternalODMParameter.g:506:4: (lv_fieldName_2_0= ruleEString )
            // InternalODMParameter.g:507:5: lv_fieldName_2_0= ruleEString
            {

            					newCompositeNode(grammarAccess.getValidatorAccess().getFieldNameEStringParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_5);
            lv_fieldName_2_0=ruleEString();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getValidatorRule());
            					}
            					set(
            						current,
            						"fieldName",
            						lv_fieldName_2_0,
            						"es.um.nosql.schemainference.dslparameter.ODMParameter.EString");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,14,FOLLOW_19); 

            			newLeafNode(otherlv_3, grammarAccess.getValidatorAccess().getColonKeyword_3());
            		
            // InternalODMParameter.g:528:3: ( (lv_ValidatorName_4_0= ruleEString ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=RULE_STRING && LA12_0<=RULE_ID)) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalODMParameter.g:529:4: (lv_ValidatorName_4_0= ruleEString )
                    {
                    // InternalODMParameter.g:529:4: (lv_ValidatorName_4_0= ruleEString )
                    // InternalODMParameter.g:530:5: lv_ValidatorName_4_0= ruleEString
                    {

                    					newCompositeNode(grammarAccess.getValidatorAccess().getValidatorNameEStringParserRuleCall_4_0());
                    				
                    pushFollow(FOLLOW_20);
                    lv_ValidatorName_4_0=ruleEString();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getValidatorRule());
                    					}
                    					set(
                    						current,
                    						"ValidatorName",
                    						lv_ValidatorName_4_0,
                    						"es.um.nosql.schemainference.dslparameter.ODMParameter.EString");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,24,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getValidatorAccess().getRightParenthesisKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValidator"


    // $ANTLR start "entryRuleUnique"
    // InternalODMParameter.g:555:1: entryRuleUnique returns [EObject current=null] : iv_ruleUnique= ruleUnique EOF ;
    public final EObject entryRuleUnique() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnique = null;


        try {
            // InternalODMParameter.g:555:47: (iv_ruleUnique= ruleUnique EOF )
            // InternalODMParameter.g:556:2: iv_ruleUnique= ruleUnique EOF
            {
             newCompositeNode(grammarAccess.getUniqueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUnique=ruleUnique();

            state._fsp--;

             current =iv_ruleUnique; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnique"


    // $ANTLR start "ruleUnique"
    // InternalODMParameter.g:562:1: ruleUnique returns [EObject current=null] : ( () ( (lv_fieldName_1_0= ruleEString ) )? ) ;
    public final EObject ruleUnique() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_fieldName_1_0 = null;



        	enterRule();

        try {
            // InternalODMParameter.g:568:2: ( ( () ( (lv_fieldName_1_0= ruleEString ) )? ) )
            // InternalODMParameter.g:569:2: ( () ( (lv_fieldName_1_0= ruleEString ) )? )
            {
            // InternalODMParameter.g:569:2: ( () ( (lv_fieldName_1_0= ruleEString ) )? )
            // InternalODMParameter.g:570:3: () ( (lv_fieldName_1_0= ruleEString ) )?
            {
            // InternalODMParameter.g:570:3: ()
            // InternalODMParameter.g:571:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getUniqueAccess().getUniqueAction_0(),
            					current);
            			

            }

            // InternalODMParameter.g:577:3: ( (lv_fieldName_1_0= ruleEString ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>=RULE_STRING && LA13_0<=RULE_ID)) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalODMParameter.g:578:4: (lv_fieldName_1_0= ruleEString )
                    {
                    // InternalODMParameter.g:578:4: (lv_fieldName_1_0= ruleEString )
                    // InternalODMParameter.g:579:5: lv_fieldName_1_0= ruleEString
                    {

                    					newCompositeNode(grammarAccess.getUniqueAccess().getFieldNameEStringParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_fieldName_1_0=ruleEString();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getUniqueRule());
                    					}
                    					set(
                    						current,
                    						"fieldName",
                    						lv_fieldName_1_0,
                    						"es.um.nosql.schemainference.dslparameter.ODMParameter.EString");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnique"


    // $ANTLR start "entryRuleUpdate"
    // InternalODMParameter.g:600:1: entryRuleUpdate returns [EObject current=null] : iv_ruleUpdate= ruleUpdate EOF ;
    public final EObject entryRuleUpdate() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUpdate = null;


        try {
            // InternalODMParameter.g:600:47: (iv_ruleUpdate= ruleUpdate EOF )
            // InternalODMParameter.g:601:2: iv_ruleUpdate= ruleUpdate EOF
            {
             newCompositeNode(grammarAccess.getUpdateRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUpdate=ruleUpdate();

            state._fsp--;

             current =iv_ruleUpdate; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUpdate"


    // $ANTLR start "ruleUpdate"
    // InternalODMParameter.g:607:1: ruleUpdate returns [EObject current=null] : ( () ( (lv_fieldName_1_0= ruleEString ) ) ) ;
    public final EObject ruleUpdate() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_fieldName_1_0 = null;



        	enterRule();

        try {
            // InternalODMParameter.g:613:2: ( ( () ( (lv_fieldName_1_0= ruleEString ) ) ) )
            // InternalODMParameter.g:614:2: ( () ( (lv_fieldName_1_0= ruleEString ) ) )
            {
            // InternalODMParameter.g:614:2: ( () ( (lv_fieldName_1_0= ruleEString ) ) )
            // InternalODMParameter.g:615:3: () ( (lv_fieldName_1_0= ruleEString ) )
            {
            // InternalODMParameter.g:615:3: ()
            // InternalODMParameter.g:616:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getUpdateAccess().getUpdateAction_0(),
            					current);
            			

            }

            // InternalODMParameter.g:622:3: ( (lv_fieldName_1_0= ruleEString ) )
            // InternalODMParameter.g:623:4: (lv_fieldName_1_0= ruleEString )
            {
            // InternalODMParameter.g:623:4: (lv_fieldName_1_0= ruleEString )
            // InternalODMParameter.g:624:5: lv_fieldName_1_0= ruleEString
            {

            					newCompositeNode(grammarAccess.getUpdateAccess().getFieldNameEStringParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_fieldName_1_0=ruleEString();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getUpdateRule());
            					}
            					set(
            						current,
            						"fieldName",
            						lv_fieldName_1_0,
            						"es.um.nosql.schemainference.dslparameter.ODMParameter.EString");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUpdate"


    // $ANTLR start "entryRuleIndex"
    // InternalODMParameter.g:645:1: entryRuleIndex returns [EObject current=null] : iv_ruleIndex= ruleIndex EOF ;
    public final EObject entryRuleIndex() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIndex = null;


        try {
            // InternalODMParameter.g:645:46: (iv_ruleIndex= ruleIndex EOF )
            // InternalODMParameter.g:646:2: iv_ruleIndex= ruleIndex EOF
            {
             newCompositeNode(grammarAccess.getIndexRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIndex=ruleIndex();

            state._fsp--;

             current =iv_ruleIndex; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIndex"


    // $ANTLR start "ruleIndex"
    // InternalODMParameter.g:652:1: ruleIndex returns [EObject current=null] : ( () ( (lv_fieldName_1_0= ruleEString ) )? otherlv_2= '->' (otherlv_3= 'kind:' ( (lv_kind_4_0= ruleIndexKind ) ) )? ) ;
    public final EObject ruleIndex() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_fieldName_1_0 = null;

        Enumerator lv_kind_4_0 = null;



        	enterRule();

        try {
            // InternalODMParameter.g:658:2: ( ( () ( (lv_fieldName_1_0= ruleEString ) )? otherlv_2= '->' (otherlv_3= 'kind:' ( (lv_kind_4_0= ruleIndexKind ) ) )? ) )
            // InternalODMParameter.g:659:2: ( () ( (lv_fieldName_1_0= ruleEString ) )? otherlv_2= '->' (otherlv_3= 'kind:' ( (lv_kind_4_0= ruleIndexKind ) ) )? )
            {
            // InternalODMParameter.g:659:2: ( () ( (lv_fieldName_1_0= ruleEString ) )? otherlv_2= '->' (otherlv_3= 'kind:' ( (lv_kind_4_0= ruleIndexKind ) ) )? )
            // InternalODMParameter.g:660:3: () ( (lv_fieldName_1_0= ruleEString ) )? otherlv_2= '->' (otherlv_3= 'kind:' ( (lv_kind_4_0= ruleIndexKind ) ) )?
            {
            // InternalODMParameter.g:660:3: ()
            // InternalODMParameter.g:661:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getIndexAccess().getIndexAction_0(),
            					current);
            			

            }

            // InternalODMParameter.g:667:3: ( (lv_fieldName_1_0= ruleEString ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=RULE_STRING && LA14_0<=RULE_ID)) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalODMParameter.g:668:4: (lv_fieldName_1_0= ruleEString )
                    {
                    // InternalODMParameter.g:668:4: (lv_fieldName_1_0= ruleEString )
                    // InternalODMParameter.g:669:5: lv_fieldName_1_0= ruleEString
                    {

                    					newCompositeNode(grammarAccess.getIndexAccess().getFieldNameEStringParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_21);
                    lv_fieldName_1_0=ruleEString();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getIndexRule());
                    					}
                    					set(
                    						current,
                    						"fieldName",
                    						lv_fieldName_1_0,
                    						"es.um.nosql.schemainference.dslparameter.ODMParameter.EString");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,25,FOLLOW_22); 

            			newLeafNode(otherlv_2, grammarAccess.getIndexAccess().getHyphenMinusGreaterThanSignKeyword_2());
            		
            // InternalODMParameter.g:690:3: (otherlv_3= 'kind:' ( (lv_kind_4_0= ruleIndexKind ) ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==26) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalODMParameter.g:691:4: otherlv_3= 'kind:' ( (lv_kind_4_0= ruleIndexKind ) )
                    {
                    otherlv_3=(Token)match(input,26,FOLLOW_23); 

                    				newLeafNode(otherlv_3, grammarAccess.getIndexAccess().getKindKeyword_3_0());
                    			
                    // InternalODMParameter.g:695:4: ( (lv_kind_4_0= ruleIndexKind ) )
                    // InternalODMParameter.g:696:5: (lv_kind_4_0= ruleIndexKind )
                    {
                    // InternalODMParameter.g:696:5: (lv_kind_4_0= ruleIndexKind )
                    // InternalODMParameter.g:697:6: lv_kind_4_0= ruleIndexKind
                    {

                    						newCompositeNode(grammarAccess.getIndexAccess().getKindIndexKindEnumRuleCall_3_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_kind_4_0=ruleIndexKind();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getIndexRule());
                    						}
                    						set(
                    							current,
                    							"kind",
                    							lv_kind_4_0,
                    							"es.um.nosql.schemainference.dslparameter.ODMParameter.IndexKind");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIndex"


    // $ANTLR start "ruleIndexKind"
    // InternalODMParameter.g:719:1: ruleIndexKind returns [Enumerator current=null] : ( (enumLiteral_0= 'Sorted' ) | (enumLiteral_1= 'Hashed' ) ) ;
    public final Enumerator ruleIndexKind() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalODMParameter.g:725:2: ( ( (enumLiteral_0= 'Sorted' ) | (enumLiteral_1= 'Hashed' ) ) )
            // InternalODMParameter.g:726:2: ( (enumLiteral_0= 'Sorted' ) | (enumLiteral_1= 'Hashed' ) )
            {
            // InternalODMParameter.g:726:2: ( (enumLiteral_0= 'Sorted' ) | (enumLiteral_1= 'Hashed' ) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==27) ) {
                alt16=1;
            }
            else if ( (LA16_0==28) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // InternalODMParameter.g:727:3: (enumLiteral_0= 'Sorted' )
                    {
                    // InternalODMParameter.g:727:3: (enumLiteral_0= 'Sorted' )
                    // InternalODMParameter.g:728:4: enumLiteral_0= 'Sorted'
                    {
                    enumLiteral_0=(Token)match(input,27,FOLLOW_2); 

                    				current = grammarAccess.getIndexKindAccess().getSortedEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getIndexKindAccess().getSortedEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalODMParameter.g:735:3: (enumLiteral_1= 'Hashed' )
                    {
                    // InternalODMParameter.g:735:3: (enumLiteral_1= 'Hashed' )
                    // InternalODMParameter.g:736:4: enumLiteral_1= 'Hashed'
                    {
                    enumLiteral_1=(Token)match(input,28,FOLLOW_2); 

                    				current = grammarAccess.getIndexKindAccess().getHashedEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getIndexKindAccess().getHashedEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIndexKind"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000038000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000748000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000088000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000708000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000088030L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000608000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000408000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000002000030L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000001000030L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000018000000L});

}
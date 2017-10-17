package generic.graphics;

import java.awt.*;
/**
 * Created by TheShrewedShrew on 10/16/17.
 */
public class Tetromino {

    private static final int UNIT = 28;
    private static final int STATE = 4;
    public enum Blocks { IBlock, OBlock, TBlock, LBlock, JBlock, SBlock, ZBlock }
    public static final boolean Tetris [][][][] = {
            {
                    {
                            {false, false, false, false},
                            {true, true, true, true},
                            {false, false, false, false},
                            {false, false, false, false}
                    },  // State 0
                    {
                            {false, false, true, false},
                            {false, false, true, false},
                            {false, false, true, false},
                            {false, false, true, false}
                    },  // State 1
                    {
                            {false, false, false, false},
                            {false, false, false, false},
                            {true, true, true, true},
                            {false, false, false, false}
                    },  // State 2
                    {
                            {false, true, false, false},
                            {false, true, false, false},
                            {false, true, false, false},
                            {false, true, false, false}
                    }   // State 3
            }, // IBlock
            {
                    {
                            {false, true, true, false},
                            {false, true, true, false},
                            {false, false, false, false},
                            {false, false, false, false}
                    },  // State 0
                    {
                            {false, true, true, false},
                            {false, true, true, false},
                            {false, false, false, false},
                            {false, false, false, false}
                    },  // State 1
                    {
                            {false, true, true, false},
                            {false, true, true, false},
                            {false, false, false, false},
                            {false, false, false, false}
                    },  // State 2
                    {
                            {false, true, true, false},
                            {false, true, true, false},
                            {false, false, false, false},
                            {false, false, false, false}
                    }   // State 3
            }, // OBlock
            {
                    {
                            {false, true, false},
                            {true, true, true},
                            {false, false, false}
                    },  // State 0
                    {
                            {false, true, false},
                            {false, true, true},
                            {false, true, false}
                    },  // State 1
                    {
                            {false, false, false},
                            {true, true, true},
                            {false, true, false}
                    },  // State 2
                    {
                            {false, true, false},
                            {true, true, false},
                            {false, true, false}
                    }   // State 3
            }, // TBlock
            {
                    {
                            {false, false, true},
                            {true, true, true},
                            {false, false, false}
                    },  // State 0
                    {
                            {false, true, false},
                            {false, true, false},
                            {false, true, true}
                    },  // State 1
                    {
                            {false, false, false},
                            {true, true, true},
                            {true, false, false}
                    },  // State 2
                    {
                            {true, true, false},
                            {false, true, false},
                            {false, true, false}
                    }   // State 3
            }, // LBlock
            {
                    {
                            {true, false, false},
                            {true, true, true},
                            {false, false, false},
                            {false, false, false}
                    },  // State 0
                    {
                            {false, true, true},
                            {false, true, false},
                            {false, true, false}
                    },  // State 1
                    {
                            {false, false, false},
                            {true, true, true},
                            {false, false, true}
                    },  // State 2
                    {
                            {false, true, false},
                            {false, true, false},
                            {true, true, false}
                    }   // State 3

            }, // JBlock
            {
                    {
                            {false, true, true},
                            {true, true, false},
                            {false, false, false}
                    },  // State 0
                    {
                            {false, true, false},
                            {false, true, true},
                            {false, false, true}
                    },  // State 1
                    {
                            {false, true, true},
                            {true, true, false},
                            {false, false, false}
                    },  // State 2
                    {
                            {false, true, false},
                            {false, true, true},
                            {false, false, true}
                    }   // State 3
            }, // SBlock
            {
                    {
                            {true, true, false},
                            {false, true, true},
                            {false, false, false}
                    },  // State 0
                    {
                            {false, false, true},
                            {false, true, true},
                            {false, true, false}
                    },  // State 1
                    {
                            {true, true, false},
                            {false, true, true},
                            {false, false, false}
                    },  // State 2
                    {
                            {false, false, true},
                            {false, true, true},
                            {false, true, false}
                    }   // State 3
            }  // ZBlock

    } ;

}

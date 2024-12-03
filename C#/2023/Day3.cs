using System.Collections.Immutable;
using System.Text.RegularExpressions;

namespace AdventOfCode2023
{
    internal class Day3
    {
        private class LineTuple
        {
            public string Line { get; init; }
            public string Previous { get; init; }
            public string Next { get; init; }

            public IEnumerable<int> PartNumbers()
            {
                Regex re = new("\\d+");
                foreach (Match match in re.Matches(Line))
                {
                    if (IsValidNumber(match.Index, match.Length))
                    {
                        yield return int.Parse(match.Value);
                    }
                }
            }

            private static bool ContainsSymbol(string line, int index, int length)
            {
                Regex re = new("[^0-9.]");
                return re.IsMatch(line.AsSpan(index - 1, length + 2));
            }

            private bool IsValidNumber(int index, int length) => ContainsSymbol(Previous, index, length) || ContainsSymbol(Next, index, length) || ContainsSymbol(Line, index, length);

            public IEnumerable<int> Gears()
            {
                Regex re = new("\\*");
                foreach (Match match in re.Matches(Line))
                {
                    var surroundingNumbers = SurroundingNumbers(match.Index);
                    if (surroundingNumbers.Count() == 2)
                    {
                        yield return surroundingNumbers.First() * surroundingNumbers.Last();
                    }
                }
            }

            private IEnumerable<int> SurroundingNumbers(int index)
            {
                Regex re = new("\\d+");
                string[] lines = [Previous, Next, Line];

                foreach (string line in lines)
                {
                    foreach (Match match in re.Matches(line))
                    {
                        if (match.Index <= index + 1 && index <= match.Index + match.Length)
                        {
                            yield return int.Parse(match.Value);
                        }
                    }
                }
            }
        }


        //....12....
        //....1.*.5
        //.......45

        private static IEnumerable<LineTuple> GetLineTuples(IEnumerable<string> lines)
        {
            lines = lines.Prepend(new('.', 140));
            lines = lines.Append(new('.', 140));

            var l = lines.Select(line => $".{line}.").ToImmutableArray();
            for (int i = 1; i < l.Length - 1; i++)
            {
                yield return new() { Line = l[i], Previous = l[i - 1], Next = l[i + 1] };
            }
        }

        public static int Exercise1()
            => GetLineTuples(File.ReadLines("Inputs/Day3.txt"))
                .Select(line => line.PartNumbers().Sum())
                .Sum();

        public static int Exercise2()
            => GetLineTuples(File.ReadLines("Inputs/Day3.txt"))
                .Select(line => line.Gears().Sum())
                .Sum();
    }
}

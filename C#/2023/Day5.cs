using System.Text.RegularExpressions;

namespace AdventOfCode2023
{
    class Day5
    {
        private class Range
        {
            public long Start { get; init; }
            public long Count { get; init; }

            public bool Index(long n, out long index)
            {
                if (Start <= n && n < Start + Count)
                {
                    index = n - Start;
                    return true;
                }
                else
                {
                    index = 0;
                    return false;
                }
            }

            public bool Intersect(Range other, out Range? intersection, out long index)
            {
                if (other.Start <= Start + Count && other.Start + other.Count > Start)
                {
                    var newStart = long.Max(Start, other.Start);
                    var newCount = long.Min(Start + Count, other.Start + other.Count) - newStart;
                    intersection = new() { Start = newStart, Count = newCount };
                    index = intersection.Start - Start;
                    return true;
                }
                else
                {
                    intersection = default;
                    index = 0;
                    return false;
                }

            }
        }

        private class Mapping
        {
            required public Range SourceRange { get; init; }
            required public long Destination { get; init; }

            public bool Map(long source, out long destination)
            {
                if (SourceRange.Index(source, out var index))
                {
                    destination = Destination + index;
                    return true;
                }
                else
                {
                    destination = source;
                    return false;
                }
            }

            public bool Map(Range range, out Range? mapped)
            {
                if (SourceRange.Intersect(range, out var intersection, out var index))
                {
                    mapped = new()
                    {
                        Start = Destination + index,
                        Count = intersection!.Count,
                    };
                    return true;
                }
                else
                {
                    mapped = default;
                    return false;
                }
            }
        }

        private class Mapper
        {
            public required IEnumerable<Mapping> Mappings { private get; init; }

            public long Map(long source)
            {
                long destination = source;
                foreach (Mapping mapping in Mappings)
                {
                    if (mapping.Map(source, out destination))
                    {
                        break;
                    }
                }
                return destination;
            }

            public IEnumerable<Range> Map(IEnumerable<Range> ranges)
            {
                foreach (Range range in ranges)
                {
                    foreach (Mapping mapping in Mappings)
                    {
                        if (mapping.Map(range, out var mapped))
                        {
                            yield return mapped!;
                        }
                    }
                }
            }
        }

        private static IEnumerable<long> GetSeeds1(string description)
        {
            Regex re = new(@"seeds:( (?<seed>\d+))+");
            Match match = re.Match(description);

            return match.Groups["seed"].Captures.Select(seed => long.Parse(seed.Value));
        }

        private static IEnumerable<Range> GetSeeds2(string description)
        {
            Regex re = new(@"seeds:( (?<seed>\d+) (?<count>\d+))+");
            Match match = re.Match(description);

            var seeds = match.Groups["seed"].Captures.Select(seed => long.Parse(seed.Value));
            var counts = match.Groups["count"].Captures.Select(seed => long.Parse(seed.Value));
            foreach (var (seed, count) in seeds.Zip(counts))
            {
                yield return new() { Start = seed, Count = count };
            }
        }

        private static List<Mapping> GetMappings(IEnumerator<string> descriptions)
        {
            List<Mapping> result = [];
            Regex re = new(@"(?<destination>\d+) (?<source>\d+) (?<count>\d+)");

            while (descriptions.MoveNext() && descriptions.Current != string.Empty)
            {
                Match match = re.Match(descriptions.Current);
                result.Add(new()
                {

                    Destination = long.Parse(match.Groups["destination"].Value),
                    SourceRange = new()
                    {
                        Start = long.Parse(match.Groups["source"].Value),
                        Count = long.Parse(match.Groups["count"].Value),
                    },
                });
            }
            return result;
        }

        private static List<Mapper> GetMappers(IEnumerator<string> descriptions)
        {
            List<Mapper> result = [];
            while (descriptions.MoveNext())
            {
                result.Add(new() { Mappings = GetMappings(descriptions) });
            }
            return result;

        }

        public static long Exercise1()
        {
            var input = File.ReadLines("Inputs/Day5.txt").GetEnumerator();

            input.MoveNext();
            var seeds = GetSeeds1(input.Current);
            input.MoveNext();

            IEnumerable<Mapper> mappers = GetMappers(input);
            return seeds.Select(seed =>
                mappers.Aggregate(seed, (seed, mapper) => mapper.Map(seed))).Min();

        }

        public static long Exercise2()
        {
            var input = File.ReadLines("Inputs/Day5.txt").GetEnumerator();

            input.MoveNext();
            var seeds = GetSeeds2(input.Current);
            input.MoveNext();

            return GetMappers(input).Aggregate(
                seeds,
                (seeds, mapper) => mapper.Map(seeds),
                ranges => ranges.Min(range => range.Start));
        }

    }
}


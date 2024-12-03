using System.Collections.Immutable;

namespace AdventOfCode2023
{
    internal class Day7
    {
        private enum HandType
        {
            HighCard = 0,
            OnePair = 1,
            TwoPair = 2,
            ThreeOfAKind = 3,
            FullHouse = 4,
            FourOfAKind = 5,
            FiveOfAKind = 6,
        }

        private class Card : IComparable<Card>
        {
            public int Value { get; }
            public char Name { get; }

            public Card(char name)
            {
                Value = name switch
                {
                    'A' => 14,
                    'K' => 13,
                    'Q' => 12,
                    'J' => 11,
                    'T' => 10,
                    var x => int.Parse(x.ToString())
                };
                Name = name;
            }

            public int CompareTo(Card? other)
                => Value.CompareTo(other?.Value);
        }

        private class Hand : IComparable<Hand>
        {
            private ImmutableArray<Card> Cards { get; }
            private HandType Type { get; }

            public int Bid { get; }

            public Hand(string description)
            {
                Cards = description.Split(" ").First().Select(name => new Card(name)).ToImmutableArray();
                var CardCounts = Cards.GroupBy(card => card.Value).Select(g => g.Count());

                Type = CardCounts.Max() switch
                {
                    1 => HandType.HighCard,
                    2 when CardCounts.Where(count => count == 2).Count() == 1
                        => HandType.OnePair,
                    2 => HandType.TwoPair,
                    3 when CardCounts.Any(count => count == 2)
                        => HandType.FullHouse,
                    3 => HandType.ThreeOfAKind,
                    4 => HandType.FourOfAKind,
                    5 => HandType.FiveOfAKind,
                    _ => throw new NotImplementedException(),
                };

                Bid = int.Parse(description.Split(" ").Last());
            }

            public int CompareTo(Hand? other)
            {
                if (other is null)
                {
                    return 1;
                }

                var typeDiff = other.Type - Type;
                if (typeDiff != 0)
                {
                    return typeDiff;
                }
                return other.Cards.Zip(Cards)
                    .Select(pair => pair.First.CompareTo(pair.Second))
                    .Where(comparison => comparison != 0)
                    .FirstOrDefault(0);
            }

            public override string ToString()
                => $"[{string.Join("", Cards.Select(x => x.Name))}] {Bid} {Type}";
        }


        public static int Exercise1()
            => File.ReadLines("Inputs/Day7.txt")
                .Select(description => new Hand(description))
                .OrderDescending()
                .Select((hand, index) => hand.Bid * (index + 1))
                .Sum();

        public static int Exercise3()
        {
            var x = File.ReadLines("Inputs/Day7.txt")
                        .Select(description => new Hand(description))
                        .Order();
            foreach (var line in x)
            {
                Console.WriteLine(line);
            }
            return 0;
        }
    }
}
